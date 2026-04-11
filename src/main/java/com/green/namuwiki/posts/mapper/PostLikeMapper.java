package com.green.namuwiki.posts.mapper;

import com.green.namuwiki.posts.dto.PostLikeRequestDTO;
import com.green.namuwiki.posts.dto.PostLikeResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostLikeMapper {

  // 좋아요 추가
  void insertLike(PostLikeRequestDTO dto);

  // 좋아요 삭제
  void deleteLike(PostLikeRequestDTO dto);

  // 좋아요 수 조회
  int countLike(Long postId);

  // 내가 좋아요 눌렀는지 확인
  int isLiked(PostLikeRequestDTO dto);

}
