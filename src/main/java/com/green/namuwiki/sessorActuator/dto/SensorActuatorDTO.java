package com.green.namuwiki.sessorActuator.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class SensorActuatorDTO {

  // 센서값
  private String deviceId;
  private LocalDateTime createDate;
  private Float tempC;
  private Float humidity;
  private Float soilMoistureValue;
  private Integer ldrValue;
  private Integer fanStatus;
  private Integer ledStatus;
  private Integer pumpStatus;

  // 임계값
  private String crops;
  private Float tempMin;
  private Float tempMax;
  private Float soilMin;
  private Float soilMax;
  private Float luxMin;
  private Float luxMax;


}
