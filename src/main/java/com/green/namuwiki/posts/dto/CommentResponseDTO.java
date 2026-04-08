package com.green.namuwiki.posts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CommentResponseDTO {
  private Long id;
  private Long postId;
  private String memEmail;
  private String memNickname;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
