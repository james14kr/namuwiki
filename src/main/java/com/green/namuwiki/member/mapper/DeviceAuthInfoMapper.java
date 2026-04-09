package com.green.namuwiki.member.mapper;

import com.green.namuwiki.member.dto.DeviceAuthInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceAuthInfoMapper {

  // 인증번호 등록 쿼리 실행
  void insertAuthCode(DeviceAuthInfoDTO deviceAuthInfoDTO);

  // 인증번호 검증 쿼리 실행
  int selectAuthCode(String authCode);

  // 인증번호 유효성 검사 시 일치여부 판단하는 쿼리 실행
  int compareFarmerData(DeviceAuthInfoDTO deviceAuthInfoDTO);


}
