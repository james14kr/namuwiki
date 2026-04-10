package com.green.namuwiki.device.mapper;

import com.green.namuwiki.device.dto.DeviceDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper {

  //관리자: 기기 ID 생성
  void insertDevice(String deviceId);

  //관리자: 전체 기기 목록 조회(농장주 + 농장물 + 최신 센서 포함)
  List<DeviceDTO> getAllDevices();

  //농장주: 기기 등록(기기 ID + 농작물 연결)
  void registerDevice(DeviceDTO dto);

  //유효성 검사: 기기 ID 존재 여부 확인
  DeviceDTO findByDeviceId(String deviceId);

}
