package com.green.namuwiki.member.mapper;

import com.green.namuwiki.member.dto.MemInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemInfoMapper {

  // 마이페이지 정보 조회 쿼리 실행 메서드
  MemInfoDTO myInfo(String memEmail);

  // 마이페이지 사진 수정 쿼리 실행 메서드
  void updateProfileImg(MemInfoDTO memInfoDTO);



}
