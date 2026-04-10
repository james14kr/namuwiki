package com.green.namuwiki.device.service;

import com.green.namuwiki.device.dto.DeviceDTO;
import com.green.namuwiki.device.mapper.DeviceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

  private final DeviceMapper deviceMapper;

  //관리자: 기기 ID 생성
  public void createDevice(String deviceId){
    deviceMapper.insertDevice(deviceId);
  }

  //관리자: 전체 기기 목록 조회(농장주 + 농장물 + 최신 센서 포함)
  public List<DeviceDTO> getAllDevices(){
    return deviceMapper.getAllDevices();
  }

  //농장주: 기기 등록(기기 ID + 농작물 연결)
  public void registerDevice(DeviceDTO dto){
    //1. 기기 ID가 존재하는지 확인
    DeviceDTO found = deviceMapper.findByDeviceId(dto.getDeviceId());
    if(found == null){
      throw new RuntimeException("존재하지 않는 기기 ID입니다.");
    }

    //2. 이미 연결된 기기인지 확인
    if(found.getIsActive() == 1){
      throw new RuntimeException("이미 다른 농작물에 연결된 기기입니다.");
    }

    //3. 기기 등록(농작물 연결)
    deviceMapper.registerDevice(dto);
  }

}
