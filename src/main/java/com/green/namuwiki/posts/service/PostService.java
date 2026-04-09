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

  public PostResponseDTO getPost(Long id) { return postMapper.findById(id); }

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


}