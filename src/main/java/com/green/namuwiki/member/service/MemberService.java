package com.green.namuwiki.member.service;

import com.green.namuwiki.member.dto.MemberDTO;
import com.green.namuwiki.member.mapper.DeviceAuthMapper;
import com.green.namuwiki.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberMapper memberMapper;
  private final PasswordEncoder passwordEncoder;
  private final DeviceAuthMapper deviceAuthMapper;

  // 회원가입 등록 실행 메서드
  public void joinData(MemberDTO memberDTO) throws Exception {
    // 입력한 비밀번호를 암호화
    String encodePw = passwordEncoder.encode(memberDTO.getMemPw());
    memberDTO.setMemPw(encodePw);

    // 농장주 일 때만 검증
    if("FARMER".equals(memberDTO.getMemRole())){
      int isValid = deviceAuthMapper.selectAuthCode(memberDTO.getAuthCode());
      if (isValid == 0){
        // 잘못 입력된 데이터를 새로운 객체로 생성해 controller의 catch Exception에 던진다.
        throw new IllegalArgumentException("인증번호가 올바르지 않습니다.");
      }
      // 인증번호 검사 통과 후 IS_USED = 'Y'로 변경
      deviceAuthMapper.updatedAuthCodeUsed(memberDTO.getAuthCode());
    }
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

  // 전체 사용자 조회
  public List<MemberDTO> memberList(){
    return memberMapper.memberList();
  }

  // 관리자 권한 사용자 추가
  public void addAdmin(MemberDTO memberDTO){
    // 입력한 비밀번호를 암호화
    String encodePw = passwordEncoder.encode(memberDTO.getMemPw());
    memberDTO.setMemPw(encodePw);

    memberMapper.addAdmin(memberDTO);
  }

  // 회원삭제 기능 실행
  public void deleteMember(String memEmail){
    memberMapper.deleteMember(memEmail);
  }

  // 권한 변경 기능 실행
  public void updateRole(MemberDTO memberDTO){
    memberMapper.updateRole(memberDTO);
  }

  // 선택한 권한에 맞는 데이터만 조회하는 기능 실행 메서드
  public List<MemberDTO> selectMemberList(String memRole){
    return memberMapper.selectMemberList(memRole);
  }

}
