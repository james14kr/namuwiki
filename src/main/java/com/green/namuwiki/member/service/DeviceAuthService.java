package com.green.namuwiki.member.service;

import com.green.namuwiki.member.dto.DeviceAuthDTO;
import com.green.namuwiki.member.mapper.DeviceAuthMapper;
import com.green.namuwiki.util.AuthCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceAuthService {
  private final DeviceAuthMapper deviceAuthMapper;
  private final AuthCodeUtil authCodeUtil;

  // 인증번호 생성 + 등록 기능 실행 메서드
  public String insertAuthCode(DeviceAuthDTO deviceAuthDTO){
    String authCode = authCodeUtil.generateAuthCode();
    deviceAuthDTO.setAuthCode(authCode);
    deviceAuthMapper.insertAuthCode(deviceAuthDTO);
    return authCode;
  }

  // 인증번호 유효성 검사 시 일치여부 판단 기능 실행 메서드
  public int compareFarmerData(DeviceAuthDTO deviceAuthDTO){
    return deviceAuthMapper.compareFarmerData(deviceAuthDTO);
  }

  // 인증번호 가진 농장주 조회 기능 실행 메서드
  public List<DeviceAuthDTO> selectAuthFarmer(){
    return deviceAuthMapper.selectAuthFarmer();
  }

}
