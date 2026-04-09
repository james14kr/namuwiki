package com.green.namuwiki.farm.service;

import com.green.namuwiki.farm.dto.FarmDTO;
import com.green.namuwiki.farm.mapper.FarmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {

  private final FarmMapper farmMapper;

  public int registerFarm(FarmDTO dto){
    return farmMapper.insertFarm(dto);
  }

  public List<FarmDTO> getFarmList() {
    return farmMapper.selectFarmList();
  }
}
