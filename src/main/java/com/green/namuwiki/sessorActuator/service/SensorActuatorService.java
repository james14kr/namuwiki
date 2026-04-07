package com.green.namuwiki.sessorActuator.service;


import com.green.namuwiki.sessorActuator.dto.SensorActuatorDTO;
import com.green.namuwiki.sessorActuator.mapper.SensorActuatorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorActuatorService {
  private final SensorActuatorMapper sensorActuatorMapper;

  // 메인피드 목록 조회 기능
  public List<SensorActuatorDTO> sensorActuator(){
    return sensorActuatorMapper.sensorActuator();
  }

}
