package com.green.namuwiki.posts.service;

import com.green.namuwiki.posts.dto.PostRequestDTO;
import com.green.namuwiki.posts.dto.PostResponseDTO;
import com.green.namuwiki.posts.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostMapper postMapper;

  public void createPost(PostRequestDTO dto) {
    postMapper.insert(dto);
  }

  // 상세 조회 + 조회수
  public PostResponseDTO getPost(Long id) {
    postMapper.incrementViewCount(id);
    return postMapper.findById(id);
  }

  public List<PostResponseDTO> getPosts() {
    return postMapper.findAll();
  }

  // 상세보기에서 게시글 삭제
  public void  deleteDetail(Long id){
    postMapper.deleteDetail(id);
  }

  // 상세보기에서 게시글 수정
  public void updatePost(PostRequestDTO postRequestDTO){
    postMapper.updatePost(postRequestDTO);
  }

  //키워드로 게시글 검색
  public List<PostResponseDTO> searchPosts(String keyword){
    return postMapper.searchByKeyword(keyword);
  }

}