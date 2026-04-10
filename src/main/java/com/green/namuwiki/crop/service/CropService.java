package com.green.namuwiki.crop.service;

import com.green.namuwiki.crop.dto.CropDTO;
import com.green.namuwiki.crop.mapper.CropMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropService {

  private final CropMapper cropMapper;

  //농작물 등록
  public int registerCrop(CropDTO dto){
    return cropMapper.insertCrop(dto);
  }

  //농작물 목록 조회
  public List<CropDTO> getCropList(int farmId){
    return cropMapper.getCropListByFarmId(farmId);
  }

  //농작물 ID로 해당 농작물 삭제
  public void deleteCrop(int cropId){
    cropMapper.deleteCrop(cropId);
  }

  //농장주 이메일로 전체 농작물 목록 반환
  public List<CropDTO> getCropListByFarmerEmail(String farmerEmail){
    return cropMapper.getCropListByFarmerEmail(farmerEmail);
  }

}
