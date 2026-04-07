package com.green.namuwiki.global.config.aws.controller;

import com.green.namuwiki.global.config.aws.dto.PresignedRequest;
import com.green.namuwiki.global.config.aws.dto.PresignedResponse;
import com.green.namuwiki.global.config.aws.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

  private final S3UploadService uploadService;

  @PostMapping("/presigned")
  //@PreAuthorize("isAuthenticated()")
  public ResponseEntity<PresignedResponse> getPresignedUrl(
          @RequestBody PresignedRequest request
  ) {
    return ResponseEntity.ok(uploadService.generatePresignedUrl(request));
  }
}