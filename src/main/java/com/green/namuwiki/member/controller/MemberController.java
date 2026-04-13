package com.green.namuwiki.member.controller;

import com.green.namuwiki.member.dto.MemberDTO;
import com.green.namuwiki.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {
  private final MemberService memberService;

  // 회원가입 api
  // url: (POST) localhost:8080/members
  @PostMapping("")
  public ResponseEntity<?> joinData(@RequestBody MemberDTO memberDTO){
    try {
      memberService.joinData(memberDTO);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    catch (IllegalArgumentException e){
      // 인증번호를 잘못 입력했을 때 발생하는 오류
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    catch (Exception e){
      log.error("회원가입 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 이메일 중복 조회 api
  // url: (POST) localhost:8080/members/memEmail
  @PostMapping("/memEmail")
  public ResponseEntity<?> checkEmail(@RequestBody MemberDTO memEmail){
    try {
      String result = memberService.searchEmail(memEmail);
      return ResponseEntity.status(HttpStatus.OK).body(result);
    }catch (Exception e){
      log.error("이메일 중복조회 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 닉네임 중복 조회 api
  // url: (POST) localhost:8080/members/memNickname
  @PostMapping("/memNickname")
  public ResponseEntity<?> searchNickname(@RequestBody MemberDTO memNickname){
    try {
      String result = memberService.searchNickname(memNickname);
      return ResponseEntity.status(HttpStatus.OK).body(result);
    }catch (Exception e){
      log.error("닉네임 중복 조회 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

//  // 아이디 찾기 api
//  // url: (GET) localhost:8080/members/memEmail
//  @GetMapping("/memEmail")
//  public ResponseEntity<?> searchEmail(MemberDTO memEmail){
//    try {
//      String emailResult = memberService.searchEmail(memEmail);
//      return ResponseEntity.status(HttpStatus.OK).body(emailResult);
//    }catch (Exception e){
//      log.error("이메일 찾기 중 오류 발생", e);
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//  }

  // 전체 사용자 조회 api
  // url: (GET) localhost:8080/members/member-list
  @GetMapping("/member-list")
  public ResponseEntity<?> memberList(){
    try {
      List<MemberDTO> memberListResult = memberService.memberList();
      return ResponseEntity.status(HttpStatus.OK).body(memberListResult);
    }catch (Exception e){
      log.error("전체 사용자 조회 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 관리자 권한 사용자 추가 api
  // 관리자만 추가 가능함.
  // url : (POST) localhost:8080/members/add-admin
  //@PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add-admin")
  public ResponseEntity<?> addAdmin(@RequestBody MemberDTO memberDTO){
    try {
      memberService.addAdmin(memberDTO);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }catch (Exception e){
      log.error("관리자 권한 사용자 추가 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 회원 삭제 api
  // url: (DELETE) localhost:8080/members/delete-member
  @DeleteMapping("/delete-member")
  public ResponseEntity<?> deleteMember(@RequestParam String memEmail){
    try{
      memberService.deleteMember(memEmail);
      return ResponseEntity.status(HttpStatus.OK).build();
    }catch (Exception e){
      log.error("회원 삭제 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 권한 변경 api
  // url: (PUT) localhost:8080/members/update-role
  @PutMapping("/update-role")
  public ResponseEntity<?> updateRole(@RequestBody MemberDTO memberDTO) {
    try {
      memberService.updateRole(memberDTO);
      return ResponseEntity.status(HttpStatus.OK).build();
    }catch (Exception e){
      log.error("권한변경 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 선택한 권한에 맞는 데이터만 조회하는 기능 실행 메서드
  // required = false : 클라이언트가 이 파라미터를 안 보내도 되는지 여부를 설정
  // required = true가 기본 값(생략하면 이게 적용)
  // url: (GET) localhost:8080/members/admin
  @GetMapping("/admin")
  public ResponseEntity<?> getMemberList(@RequestParam(required = false) String memRole){
    try {
      return ResponseEntity.status(HttpStatus.OK).body(memberService.selectMemberList(memRole));
    }catch (Exception e){
      log.error("선택한 권한에 맞는 리스트 조회 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

}
