package com.green.namuwiki.member.service;

import com.green.namuwiki.member.dto.DeviceAuthInfoDTO;
import com.green.namuwiki.member.mapper.DeviceAuthInfoMapper;
import com.green.namuwiki.util.AuthCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceAuthInfoService {
  private final DeviceAuthInfoMapper deviceAuthInfoMapper;
  private final AuthCodeUtil authCodeUtil;

  // 인증번호 생성 + 등록 기능 실행 메서드
  public String insertAuthCode(DeviceAuthInfoDTO deviceAuthInfoDTO){
    String authCode = authCodeUtil.generateAuthCode();
    deviceAuthInfoDTO.setAuthCode(authCode);
    deviceAuthInfoMapper.insertAuthCode(deviceAuthInfoDTO);
    return authCode;
  }
}
