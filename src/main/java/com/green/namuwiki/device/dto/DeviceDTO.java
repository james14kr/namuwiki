package com.green.namuwiki.device.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeviceDTO {
  private String deviceId;
  private int cropId;
  private String farmerEmail;
  private int isActive;
  private String registeredAt;
  private String createAt;

  private String cropName;
  private String farmName;
}
