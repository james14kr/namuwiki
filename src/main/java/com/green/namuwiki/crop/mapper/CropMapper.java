package com.green.namuwiki.crop.mapper;

import com.green.namuwiki.crop.dto.CropDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CropMapper {

  //농작물 등록
  int insertCrop(CropDTO dto);

  //농작물 목록 조회
  List<CropDTO> getCropListByFarmId(int farmId);

}
