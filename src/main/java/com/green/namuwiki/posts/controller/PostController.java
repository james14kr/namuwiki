package com.green.namuwiki.posts.controller;

import com.green.namuwiki.posts.dto.PostRequestDTO;
import com.green.namuwiki.posts.dto.PostResponseDTO;
import com.green.namuwiki.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<Void> createPost(@RequestBody PostRequestDTO dto) {
    System.out.println("게시글 등록 요청 dto: " + dto);
    postService.createPost(dto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long id) {
    return ResponseEntity.ok(postService.getPost(id));
  }

  @GetMapping
  public ResponseEntity<List<PostResponseDTO>> getPosts() {
    return ResponseEntity.ok(postService.getPosts());
  }

  // 상세보기에서 게시글 삭제 api
  // (delete) localhost:8080/posts/3
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteDetail(@PathVariable("id") Long id){
    try {
      postService.deleteDetail(id);
      System.out.println(id+" 상품이 정상적으로 삭제됨");
      return ResponseEntity.status(HttpStatus.OK).build();

    } catch (Exception e){
      log.error("상세보기 게시글 삭제 중 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 상세보기 게시글 수정 api
  // (put) localhost:8080/posts/3
  @PutMapping("/{id}")
  public ResponseEntity<?> updatePost(
      @PathVariable("id") Long id
      , @RequestBody PostRequestDTO postRequestDTO){
    try {
      postRequestDTO.setId(id);
      postService.updatePost(postRequestDTO);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e){
      log.error("상세보기 게시글 수정 중 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }



}





