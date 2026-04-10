package com.green.namuwiki.farm.service;

import com.green.namuwiki.crop.mapper.CropMapper;
import com.green.namuwiki.farm.dto.FarmDTO;
import com.green.namuwiki.farm.mapper.FarmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {

  private final FarmMapper farmMapper;
  private final CropMapper cropMapper;

  public int registerFarm(FarmDTO dto){
    return farmMapper.insertFarm(dto);
  }

  public List<FarmDTO> getFarmList() {
    return farmMapper.selectFarmList();
  }

  public FarmDTO getFarmById(int farmId){
    return farmMapper.selectFarmById(farmId);
  }

  public List<FarmDTO> getMyFarms(String farmerEmail){
    return farmMapper.selectFarmsByEmail(farmerEmail);
  }

  public void deleteFarm(int farmId){
    //1. 농작물 먼저 삭제
    // CROP 테이블이 FARM_ID를 KF로 참조하고 있어 농장을 먼저 삭제하면 FK 제약조건 위반 오류 발생
    cropMapper.deleteCropByFarmId(farmId);

    //2. 농장 삭제
    farmMapper.deleteFarm(farmId);
  }

}
