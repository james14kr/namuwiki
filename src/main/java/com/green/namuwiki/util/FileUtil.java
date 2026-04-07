package com.green.namuwiki.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {
    // properties 값 가져오기
    private final String bucket;

    public FileUtil(
            @Value("${cloud.aws.s3.bucket}") String bucket
    ) {
        this.bucket = bucket;
    }

    public String getFullUrl(String imgUrl) {
        return "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/" + imgUrl;
    }
}
