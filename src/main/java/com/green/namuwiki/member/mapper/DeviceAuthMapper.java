package com.green.namuwiki.member.mapper;

import com.green.namuwiki.member.dto.DeviceAuthDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceAuthMapper {

  // 인증번호 등록 쿼리 실행
  void insertAuthCode(DeviceAuthDTO deviceAuthDTO);

  // 인증번호 검증 쿼리 실행
  int selectAuthCode(String authCode);

  // 인증번호 유효성 검사 시 일치여부 판단하는 쿼리 실행
  int compareFarmerData(DeviceAuthDTO deviceAuthDTO);

  // 인증번호 가진 농장주 조회하는 쿼리 실행 메서드
  List<DeviceAuthDTO> selectAuthFarmer();

  // 미등록 농장주 조회하는 쿼리 실행 메서드
  int selectUnregFarmer();
}
