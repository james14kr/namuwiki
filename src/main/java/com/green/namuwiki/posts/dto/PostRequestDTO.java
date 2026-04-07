package com.green.namuwiki.posts.dto;

import lombok.Getter;

@Getter
public class PostRequestDTO {
  private Long id;
  private String title;
  private String content;
}