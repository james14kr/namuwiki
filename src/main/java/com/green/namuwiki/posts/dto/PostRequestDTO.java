package com.green.namuwiki.posts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class PostRequestDTO {
  private Long id;
  private String title;
  private String content;
}