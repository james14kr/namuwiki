package com.green.namuwiki.sessorActuator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorHistoryDTO {
  private String createDate;
  private double tempC;
  private double humidity;
  private double soilMoistureValue;
  private double ldrValue;
}
