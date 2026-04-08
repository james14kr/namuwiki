package com.green.namuwiki.posts.controller;


import com.green.namuwiki.posts.dto.CommentRequestDTO;
import com.green.namuwiki.posts.dto.CommentResponseDTO;
import com.green.namuwiki.posts.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.spec.ECField;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;

  // 댓글 등록 api
  // (post) localhost:8080/api/comments
  @PostMapping
  public ResponseEntity<?> insertComment(@RequestBody CommentRequestDTO commentRequestDTO){
    try {
      commentService.insertComment(commentRequestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (Exception e){
      log.error("댓글 등록 중 오류", e);
      return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 댓글 조회 api
  // (get) localhost:8080/api/comments/3
  @GetMapping("/{postId}")
  public ResponseEntity<?> selectComment(@PathVariable("postId") Long postId){
    try {
      List<CommentResponseDTO> response = commentService.selectComment(postId);
      return ResponseEntity.status(HttpStatus.OK).body(response);

    } catch (Exception e){
      log.error("댓글 조회 중 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 댓글 수정 api
  // (put) localhost:8080/api/comments/7
  @PutMapping("/{id}")
  public ResponseEntity<?> updateComment(
      @PathVariable("id") Long id,
      @RequestBody CommentRequestDTO commentRequestDTO
  ){
    try {
      commentRequestDTO.setId(id);
      commentService.updateComment(commentRequestDTO);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e){
      log.error("댓글 수정 중 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 댓글 삭제 api
  // (delete) localhost:8080/api/comments/9
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
    try {
      commentService.deleteComment(id);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e){
      log.error("댓글 삭제 중 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

  }



}
