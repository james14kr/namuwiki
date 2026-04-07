package com.green.namuwiki.member.controller;

import com.green.namuwiki.member.dto.DeviceAuthInfoDTO;
import com.green.namuwiki.member.service.DeviceAuthInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authes")
@Slf4j
public class DeviceAuthInfoController {
  private final DeviceAuthInfoService deviceAuthInfoService;

  // 인증번호 등록 api
  // url: (POST) localhost:8080/members/authCode
  @PostMapping("/authCode")
  public ResponseEntity<?> insertAuthCode(@RequestBody DeviceAuthInfoDTO deviceAuthInfoDTO){
    try {
      deviceAuthInfoService.insertAuthCode(deviceAuthInfoDTO);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }catch (Exception e){
      log.error("인증번호 api 등록 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
