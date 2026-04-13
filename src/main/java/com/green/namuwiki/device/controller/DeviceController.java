package com.green.namuwiki.device.controller;

import com.green.namuwiki.device.dto.DeviceDTO;
import com.green.namuwiki.device.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/device")
public class DeviceController {

  private final DeviceService deviceService;

  //관리자: 기기 ID 생성
  @PostMapping("/admin")
  public ResponseEntity<?> createDevice(@RequestParam String deviceId){
    deviceService.createDevice(deviceId);
    return ResponseEntity.ok().build();
  }

  // 관리자: 전체 기기 목록 조회
  // GET /device/admin
  @GetMapping("/admin")
  public ResponseEntity<List<DeviceDTO>> getAllDevices(){
    return ResponseEntity.ok(deviceService.getAllDevices());
  }

  //농장주: 기기 등록(기기 ID + 농작물 연결)
  // POST /device/farmer
  @PostMapping("/register")
  public ResponseEntity<?> registerDevice(@RequestBody DeviceDTO dto){
    deviceService.registerDevice(dto);
    return ResponseEntity.ok().build();
  }

  //농장주: 나의 기기 목록 조회
  //GET /device/my
  @GetMapping("/my")
  public ResponseEntity<List<DeviceDTO>> getMyDevices(@RequestParam String farmerEmail){
    return ResponseEntity.ok(deviceService.getMyDevices(farmerEmail));
  }

}
