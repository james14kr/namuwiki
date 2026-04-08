package com.green.namuwiki.farm.mapper;

import com.green.namuwiki.farm.dto.FarmDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FarmMapper {

  int insertFarm(FarmDTO dto);

}
