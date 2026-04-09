package com.green.namuwiki.member.mapper;

import com.green.namuwiki.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
  // 회원가입 등록 쿼리 실행 메서드
  void joinData(MemberDTO memberDTO);

  // 로그인 검증 시 로그인 정보 조회 쿼리 실행 메서드
  MemberDTO selectLoginInfo(String memEmail);

  // 이메일 중복 조회 쿼리 실행 메서드
  String searchEmail(MemberDTO memEmail);

  // 닉네임 중복 조회 쿼리 실행
  String searchNickname(MemberDTO memNickname);

  // 전체 사용자 조회
  MemberDTO memberList();
}
