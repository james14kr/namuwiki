package com.green.namuwiki.follow.controller;

import com.green.namuwiki.follow.dto.FollowDTO;
import com.green.namuwiki.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

  private final FollowService followService;

  /*팔로우*/
  @PostMapping
  public ResponseEntity<?> follow(@RequestBody FollowDTO dto){
    followService.follow(dto);
    return ResponseEntity.ok().build();
  }

  /*언팔로우*/
  @DeleteMapping
  public ResponseEntity<?> unfollow(@RequestBody FollowDTO dto){
    followService.unfollow(dto);
    return ResponseEntity.ok().build();
  }

  /*팔로우 목록 조회*/
  @GetMapping
  public ResponseEntity<List<FollowDTO>> getFollowList(@RequestParam String followerEmail){
    return ResponseEntity.ok(followService.getFollowList(followerEmail));
  }

  /*팔로우 여부 확인*/
  @GetMapping("/check")
  public ResponseEntity<Boolean> checkFollow(@ModelAttribute FollowDTO dto){
    Boolean result = followService.checkFollow(dto);
    return ResponseEntity.ok(result);
  }

}
