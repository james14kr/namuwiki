package com.green.namuwiki.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class MemberDTO {
  private String memEmail;
  private String memPw;
  private String memRole; //권한
  private String memNickname;
  private String memName;
  private String memTel;
  private String memAdd;
  private String addDetail;
  private LocalDateTime memJoinDate;
  private String farmerName;
  private String authCode;
}
