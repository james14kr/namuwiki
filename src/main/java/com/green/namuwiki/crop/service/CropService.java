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
}
