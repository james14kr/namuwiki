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
public class PostLikeResponseDTO {
  private int likeCount;

  // 내가 좋아요 했는지 여부
  private boolean liked;
}
