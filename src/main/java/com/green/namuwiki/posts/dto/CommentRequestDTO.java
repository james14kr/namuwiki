package com.green.namuwiki.posts.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentRequestDTO {
  private Long id;
  private Long postId;
  private String memEmail;
  private String content;
}
