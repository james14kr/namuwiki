package com.green.namuwiki.sessorActuator.mapper;


import com.green.namuwiki.sessorActuator.dto.SensorActuatorDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SensorActuatorMapper {

  // 메인피드 목록 조회 쿼리 실행 메서드
  List<SensorActuatorDTO> sensorActuator();


}
