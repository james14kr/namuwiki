package com.green.namuwiki.member.service;


import com.green.namuwiki.global.config.aws.service.S3UploadService;
import com.green.namuwiki.member.dto.MemInfoDTO;
import com.green.namuwiki.member.mapper.MemInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemInfoService {
  private final MemInfoMapper memInfoMapper;
  private final S3UploadService service;


  // 마이페이지 정보 조회 기능
  public MemInfoDTO myInfo(String memEmail){
    return memInfoMapper.myInfo(memEmail);
  }

  public void deleteProfileImg() {
    service.deleteImage("");
  }


}
