package com.green.namuwiki.member.mapper;

import com.green.namuwiki.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
  List<MemberDTO> memberList();

  // 관리자권한 사용자 추가
  void addAdmin(MemberDTO memberDTO);

  // 회원삭제 쿼리 실행
  void deleteMember(String memEmail);

  // 권한 변경 쿼리 실행
  void updateRole(MemberDTO memberDTO);

  // 선택한 권한에 맞는 데이터만 조회하는 쿼리 실행 메서드
  // @Param : "이 매개변수를 xml에서 memRole이라는 이름으로 찾을 수 있게 등록해줘~" 라는 의미
  // => xml에서 이름으로 접근 가능해짐, 매개변수가 1개(String, int형), 매개변수가 2개 이상일 때 필요
  List<MemberDTO> selectMemberList(@Param("memRole") String memRole);

}
