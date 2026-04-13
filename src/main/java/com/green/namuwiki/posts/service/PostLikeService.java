package com.green.namuwiki.posts.service;

import com.green.namuwiki.posts.dto.PostLikeRequestDTO;
import com.green.namuwiki.posts.dto.PostLikeResponseDTO;
import com.green.namuwiki.posts.mapper.PostLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostLikeService {

  private final PostLikeMapper postLikeMapper;

  // 좋아요 기능(누르면추가, 또 누르면 삭제)
  public PostLikeResponseDTO toggleLike(PostLikeRequestDTO postLikeRequestDTO){
    int isLiked = postLikeMapper.isLiked(postLikeRequestDTO);

    // 이미 좋아요라면 삭제
    if(isLiked > 0){
      postLikeMapper.deleteLike(postLikeRequestDTO);
    } else {
      // 아직 좋아요 아니라면 추가
      postLikeMapper.insertLike(postLikeRequestDTO);
    }
    int likeCount = postLikeMapper.countLike(postLikeRequestDTO.getPostId());
    boolean liked = isLiked == 0;

    return new PostLikeResponseDTO(likeCount, liked);
  }

  // 좋아요 상태 조회
  public PostLikeResponseDTO getLikeStatus(Long postId, String memEmail){
    PostLikeRequestDTO response = new PostLikeRequestDTO();
    response.setPostId(postId);
    response.setMemEmail(memEmail);

    int likeCount = postLikeMapper.countLike(postId);
    boolean liked = postLikeMapper.isLiked(response) > 0;

    return new PostLikeResponseDTO(likeCount, liked);
  }



}