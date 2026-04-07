package com.green.namuwiki.member.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;



@Getter
@Setter
@ToString
public class MemInfoDTO {
  private String memEmail;
  private String memRole;
  private String memNickname;
  private String memName;
  private String memTel;
  private String memAdd;
  private LocalDateTime memJoinDate;
  private String memProfileImg;


}
