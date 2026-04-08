package com.green.namuwiki.posts.service;


import com.green.namuwiki.posts.dto.CommentRequestDTO;
import com.green.namuwiki.posts.dto.CommentResponseDTO;
import com.green.namuwiki.posts.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentMapper commentMapper;

  // 댓글 등록 기능
  public void insertComment(CommentRequestDTO commentRequestDTO){
    commentMapper.insertComment(commentRequestDTO);
  }

  // 댓글 조회 기능
  public List<CommentResponseDTO> selectComment(Long postId){
    return commentMapper.selectComment(postId);
  }

}
