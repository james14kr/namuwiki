package com.green.namuwiki.global.config.aws.service;

import com.green.namuwiki.global.config.aws.dto.PresignedRequest;
import com.green.namuwiki.global.config.aws.dto.PresignedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3UploadService {
  private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
  private static final List<String> ALLOWED_TYPES = List.of("image/jpeg", "image/png", "image/webp", "image/gif");

  private final S3Presigner s3Presigner;
  private final S3Client s3Client;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${cloud.aws.s3.public-url}")
  private String publicUrl;

  public PresignedResponse generatePresignedUrl(PresignedRequest request) {
    if (!ALLOWED_TYPES.contains(request.getContentType())) {
      throw new IllegalArgumentException("지원하지 않는 파일 형식입니다");
    }
    if (request.getFileSize() > MAX_FILE_SIZE) {
      throw new IllegalArgumentException("파일 크기가 5MB를 초과합니다");
    }

    String key = request.getFolder() + "/" + UUID.randomUUID() + "_" + sanitizeFilename(request.getFilename());

    PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(10))
            .putObjectRequest(r -> r
                    .bucket(bucket)
                    .key(key)
                    .contentType(request.getContentType())
                    .build())
            .build();

    String presignedUrl = s3Presigner.presignPutObject(presignRequest).url().toString();

    return new PresignedResponse(presignedUrl, publicUrl + "/" + key);
  }

  public void deleteImage(String publicUrl) {
    // 요거 publicUrl에서 경로랑 파일명만 추출해서 키로 만들어야 해가지고, replace 추가함
    // 참고로 저 this.publicUrl과 파라미터 publicUrl 다른거니까 헷갈리면 안됨
    String key = publicUrl.replace(this.publicUrl + "/", "");

    s3Client.deleteObject(d -> d
            .bucket(bucket)
            .key(key)
            .build());
  }

  private String sanitizeFilename(String filename) {
    return filename.replaceAll("[^a-zA-Z0-9._-]", "_");
  }
}
