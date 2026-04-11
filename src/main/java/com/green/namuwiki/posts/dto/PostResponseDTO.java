package com.green.namuwiki.posts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
@Setter
public class PostResponseDTO {
  private Long id;
  private String title;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String memEmail;
  private String memNickname;
  private String memProfileImg;
  private int viewCount;
}
