package com.green.namuwiki.member.controller;

import com.green.namuwiki.member.dto.DeviceAuthDTO;
import com.green.namuwiki.member.service.DeviceAuthService;
import com.green.namuwiki.util.AuthCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authes")
@Slf4j
public class DeviceAuthController {
  private final DeviceAuthService deviceAuthService;
  private final AuthCodeUtil authCodeUtil;

  // 인증번호 등록 api
  // url: (POST) localhost:8080/authes/authCode
  @PostMapping("/authCode")
  public ResponseEntity<?> insertAuthCode(@RequestBody DeviceAuthDTO deviceAuthDTO){
    try {
      String authCode = authCodeUtil.generateAuthCode();
      deviceAuthService.insertAuthCode(deviceAuthDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(authCode);
    }catch (Exception e){
      log.error("인증번호 api 등록 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 인증번호 유효성검사 정보 조회 api
  // url: (POST) localhost:8080/authes/check-auth
  @PostMapping("/check-auth")
  public ResponseEntity<?> compareFarmerData(@RequestBody DeviceAuthDTO deviceAuthDTO){
    try {
      int result = deviceAuthService.compareFarmerData(deviceAuthDTO);
      return ResponseEntity.status(HttpStatus.OK).body(result);
    }catch (Exception e){
      log.error("인증번호 유효성검사 정보 조회 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 인증번호 가진 농장주 조회
  // url: (GET) localhost:8080/authes
  @GetMapping("")
  public ResponseEntity<?> selectAuthFarmer(){
    try {
      List<DeviceAuthDTO> farmerList = deviceAuthService.selectAuthFarmer();
      return ResponseEntity.status(HttpStatus.OK).body(farmerList);
    }catch (Exception e){
      log.error("인증번호 가진 농장주 조회 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }


}
