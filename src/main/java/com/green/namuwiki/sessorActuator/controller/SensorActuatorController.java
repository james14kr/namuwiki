package com.green.namuwiki.sessorActuator.controller;


import com.green.namuwiki.sessorActuator.dto.SensorActuatorDTO;
import com.green.namuwiki.sessorActuator.dto.SensorHistoryDTO;
import com.green.namuwiki.sessorActuator.service.SensorActuatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sensorActuator")
@RequiredArgsConstructor
public class SensorActuatorController {
  private final SensorActuatorService sensorActuatorService;

  //cropId로 초신 센서 데이터 조회
  @GetMapping("/latest/crop")
  public ResponseEntity<?> getLatestByCropId(@RequestParam int cropId){
    SensorActuatorDTO data = sensorActuatorService.getLatestByCropId(cropId);
    if(data == null){
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(data);
  }

  @GetMapping("/history/crop")
  public ResponseEntity<List<SensorHistoryDTO>> getLatestByCropId(@RequestParam int cropId, @RequestParam(defaultValue = "20") int limit){
    List<SensorHistoryDTO> data = sensorActuatorService.getHistoryByCropId(cropId, limit);
    return ResponseEntity.ok(data);
  }


}
