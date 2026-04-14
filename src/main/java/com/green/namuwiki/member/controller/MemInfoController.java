package com.green.namuwiki.member.controller;


import com.green.namuwiki.member.dto.MemInfoDTO;
import com.green.namuwiki.member.service.MemInfoService;
import com.green.namuwiki.posts.dto.CommentResponseDTO;
import com.green.namuwiki.posts.dto.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/mypage")
@RestController
@RequiredArgsConstructor
public class MemInfoController {
  private final MemInfoService memInfoService;

  // 마이페이지 조회 api
  // (get) localhost:8080/mypage?memEmail=test@test.com
  @GetMapping("")
  public ResponseEntity<?> myInfo(@RequestParam("memEmail") String memEmail){
    try {
      MemInfoDTO memInfo = memInfoService.myInfo(memEmail);
      return ResponseEntity.status(HttpStatus.OK).body(memInfo);
    } catch (Exception e){
      log.error("마이페이지 조회 중 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 마이페이지 사진 수정 api
  // (put) localhost:8080/mayage/profile-img
  @PutMapping("/profile-img")
  public ResponseEntity<?> updateProfileImg(@RequestBody MemInfoDTO memInfoDTO){
    try {
      memInfoService.updateProfileImg(memInfoDTO);
      return ResponseEntity.status(HttpStatus.OK).build();

    } catch (Exception e){
      log.error("사진 등록 수정 중 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }


  // 내가 작성한 게시글 조회 api
  // (get) localhost:8080/mypage/posts?memEmail=user1
  @GetMapping("/posts")
  public ResponseEntity<?> findMyPosts(@RequestParam("memEmail") String memEmail){
    try {
      List<PostResponseDTO> response = memInfoService.findMyPosts(memEmail);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e){
      log.error("내가 작성한 게시글 조회 중 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }


  // 내가 작성한 댓글 조회 api
  // (get) localhost:8080/mypage/comments?memEmail=user1
  @GetMapping("/comments")
  public ResponseEntity<?> findMyComments(@RequestParam("memEmail") String memEmail){
    try {
      List<CommentResponseDTO> response = memInfoService.findMyComments(memEmail);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e){
      log.error("내가 작성한 댓글 조호 ㅣ중 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }





















}
