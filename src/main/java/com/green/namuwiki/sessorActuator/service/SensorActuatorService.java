package com.green.namuwiki.sessorActuator.service;


import com.green.namuwiki.sessorActuator.dto.SensorActuatorDTO;
import com.green.namuwiki.sessorActuator.dto.SensorHistoryDTO;
import com.green.namuwiki.sessorActuator.mapper.SensorActuatorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorActuatorService {
  private final SensorActuatorMapper sensorActuatorMapper;

  // cropId로 최신 센서 데이터 조회
  public SensorActuatorDTO getLatestByCropId(int cropId){
    return sensorActuatorMapper.getLatestByCropId(cropId);
  }

  public List<SensorHistoryDTO> getHistoryByCropId(int cropId, int limit, String startDate){
    return sensorActuatorMapper.getHistoryByCropId(cropId, limit, startDate);
  }

}
