package com.green.namuwiki.posts.controller;

import com.green.namuwiki.posts.dto.PostLikeRequestDTO;
import com.green.namuwiki.posts.dto.PostLikeResponseDTO;
import com.green.namuwiki.posts.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class PostLikeController {

  private final PostLikeService postLikeService;

  // 좋아요 토글 api
  // (post) localhost:8080/api/likes
  @PostMapping
  public ResponseEntity<?> toggleLike(@RequestBody PostLikeRequestDTO postLikeRequestDTO){
    try {
      PostLikeResponseDTO response =  postLikeService.toggleLike(postLikeRequestDTO);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e){
      log.error("좋아요 토글 중 오류",e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 좋아요 상태 조회 api
  // (get) localhost:8080/api/likes/35?memEmail=...@naver.com
  @GetMapping("/{postId}")
  public ResponseEntity<?> getLikeStatus(
      @PathVariable("postId") Long postId,
      @RequestParam("memEmail") String memEmail){
    try {
      PostLikeResponseDTO response = postLikeService.getLikeStatus(postId,memEmail);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e){
      log.error("좋아요 상태 조회 중 오류",e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

  }

}





