package com.green.namuwiki.farm.mapper;

import com.green.namuwiki.farm.dto.FarmDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FarmMapper {

  //농장 등록
  int insertFarm(FarmDTO dto);

  //농장 목록
  List<FarmDTO> selectFarmList();

  //팔로우한 농장
  FarmDTO selectFarmById(int farmId);

  //농장주가 등록한 노우장 목록
  List<FarmDTO> selectFarmsByEmail(String farmerEmail);

}
