package com.green.namuwiki.device.dto;

import lombok.Data;

@Data
public class DeviceControlDTO {

  private String deviceId;
  private Integer fanOverride;
  private Integer ledOverride;
  private Integer pumpOverride;

}
