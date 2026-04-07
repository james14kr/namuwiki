package com.green.namuwiki.sessorActuator.controller;


import com.green.namuwiki.sessorActuator.dto.SensorActuatorDTO;
import com.green.namuwiki.sessorActuator.service.SensorActuatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sensorActuator")
@RequiredArgsConstructor
public class SensorActuatorController {
  private final SensorActuatorService sensorActuatorService;

  // 가장 최근 센서 액츄에이터 데이터 목록 조회 api
  // (get) localhost:8080/sensorActuator/latest
  @GetMapping("/latest")
  public ResponseEntity<?> sensorActuator(){
    try{
      List<SensorActuatorDTO> result = sensorActuatorService.sensorActuator();
      return ResponseEntity.status(HttpStatus.OK).body(result);
    }catch (Exception e){
      log.error("센서 액츄에이터 목록 조회 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

  }



}
