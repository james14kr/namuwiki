package com.green.namuwiki.member.service;

import com.green.namuwiki.member.dto.MemberDTO;
import com.green.namuwiki.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberMapper memberMapper;
  private final PasswordEncoder passwordEncoder;

  // 회원가입 등록 실행 메서드
  public void joinData(MemberDTO memberDTO){
    // 입력한 비밀번호를 암호화
    String encodePw = passwordEncoder.encode(memberDTO.getMemPw());
    memberDTO.setMemPw(encodePw);

    memberMapper.joinData(memberDTO);
  }

  // 로그인 검증시 필요한 로그인 정보 조회 실행 메서드
  public MemberDTO selectLoginInfo(String memEmail){
    return memberMapper.selectLoginInfo(memEmail);
  }

  // 이메일 중복 조회 기능 실행 메서드
  public String searchEmail(MemberDTO memEmail){
    return memberMapper.searchEmail(memEmail);
  }

  // 닉네임 중복 조회 기능 실행 메서드
  public String searchNickname(MemberDTO memNickname){
    return memberMapper.searchNickname(memNickname);
  }




}
