package com.green.namuwiki.posts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class PostLikeRequestDTO {
  private Long postId;
  private String memEmail;
}