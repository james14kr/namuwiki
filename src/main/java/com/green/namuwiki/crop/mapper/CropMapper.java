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

  //농장주 이메일로 해당 농장주의 모든 농작물 조회
  List<CropDTO> getCropListByFarmerEmail(String farmerEmail);

  //특정 농장에 속한 농작물을 모두 삭제
  void deleteCropByFarmId(int farmId);

  //농작물 ID로 해당하는 농작물 삭제
  void deleteCrop(int cropId);

}
