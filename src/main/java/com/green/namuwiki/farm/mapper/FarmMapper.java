package com.green.namuwiki.farm.mapper;

import com.green.namuwiki.farm.dto.FarmDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FarmMapper {

  int insertFarm(FarmDTO dto);

  List<FarmDTO> selectFarmList();

  FarmDTO selectFarmById(int farmId);

}
