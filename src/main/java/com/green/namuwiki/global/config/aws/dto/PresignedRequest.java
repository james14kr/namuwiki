package com.green.namuwiki.global.config.aws.dto;

import lombok.Getter;

@Getter
public class PresignedRequest {
  private String folder;
  private String filename;
  private String contentType;
  private Long fileSize;
}