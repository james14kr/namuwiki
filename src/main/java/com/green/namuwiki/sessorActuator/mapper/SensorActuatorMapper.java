package com.green.namuwiki.sessorActuator.mapper;


import com.green.namuwiki.sessorActuator.dto.SensorActuatorDTO;
import com.green.namuwiki.sessorActuator.dto.SensorHistoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SensorActuatorMapper {
  // cropId로 최신 센서 데이터 1건 조회
  SensorActuatorDTO getLatestByCropId(@Param("cropId") int cropId);

  List<SensorHistoryDTO> getHistoryByCropId(@Param("cropId") int cropId, @Param("limit") int limit);

}
