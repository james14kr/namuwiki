package com.green.namuwiki.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DeviceAuthDTO {
  private int farmId;
  private String authCode;
  private String memName;
  private String memTel;
  private String isUsed;
  private LocalDateTime createTime;
}
