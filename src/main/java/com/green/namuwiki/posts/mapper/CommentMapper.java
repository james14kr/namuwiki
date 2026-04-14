package com.green.namuwiki.posts.mapper;

import com.green.namuwiki.posts.dto.CommentRequestDTO;
import com.green.namuwiki.posts.dto.CommentResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

  // 댓글 등록 쿼리 실행 메서드
  void insertComment(CommentRequestDTO commentRequestDTO);

  // 게시글별 댓글 목록 조회 쿼리 실행 메서드
  List<CommentResponseDTO> selectComment(Long postId);

  // 댓글 수정 쿼리 실행 메서드
  void updateComment(CommentRequestDTO commentRequestDTO);

  // 댓글 삭제 쿼리 실행 메서드
  void deleteComment(Long id);

  // 내가 작성한 댓글 조회 쿼리 실행 메서드
  List<CommentResponseDTO> findByMemEmail(String memEmail);


}
