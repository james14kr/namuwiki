package com.green.namuwiki.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DeviceAuthInfoDTO {
  private int farmId;
  private String authCode;
  private String farmerName;
  private String farmerTel;
  private String isUsed;
  private LocalDateTime createTime;
}
