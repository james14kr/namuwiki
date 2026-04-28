package com.green.namuwiki.device.mapper;

import com.green.namuwiki.device.dto.DeviceControlDTO;
import com.green.namuwiki.device.dto.DeviceDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeviceMapper {

  //관리자: 기기 ID 생성
  void insertDevice(String deviceId);

  //관리자: 전체 기기 목록 조회(농장주 + 농장물 + 최신 센서 포함)
  List<DeviceDTO> getAllDevices();

  //농장주: 기기 등록(기기 ID + 농작물 연결)
  void registerDevice(DeviceDTO dto);

  //농장주: 이메일로 기기 목록 조회
  List<DeviceDTO> getDevicesByFarmerEmail(@Param("farmerEmail") String farmerEmail);

  //농장주: 농작물에서 기기 연결 해제
  void unlinkDeviceByCropId(@Param("cropId") int cropId);

  //유효성 검사: 기기 ID 존재 여부 확인
  DeviceDTO findByDeviceId(String deviceId);

  //수동 제어 override 업데이트
  void updateDeviceControl(DeviceControlDTO dto);

}
