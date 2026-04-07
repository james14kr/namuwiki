package com.green.namuwiki.member.controller;


import com.green.namuwiki.member.dto.MemInfoDTO;
import com.green.namuwiki.member.service.MemInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
