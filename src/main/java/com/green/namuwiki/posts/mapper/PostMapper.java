package com.green.namuwiki.posts.mapper;

import com.green.namuwiki.posts.dto.PostRequestDTO;
import com.green.namuwiki.posts.dto.PostResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
  void insert(PostRequestDTO dto);

  PostResponseDTO findById(Long id);

  List<PostResponseDTO> findAll();

  // 상세보기에서 게시글 삭제 쿼리 실행 메서드
  void deleteDetail(Long id);

  // 상세보기에서 게시글 수정 쿼리 실행 메서드
  void updatePost(PostRequestDTO postRequestDTO);

  // 조회수 쿼리 실행 메서드
  void incrementViewCount(Long id);

}
