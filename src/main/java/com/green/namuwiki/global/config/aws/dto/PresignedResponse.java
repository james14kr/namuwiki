package com.green.namuwiki.global.config.aws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PresignedResponse {
  private String presignedUrl;
  private String publicUrl;
}
