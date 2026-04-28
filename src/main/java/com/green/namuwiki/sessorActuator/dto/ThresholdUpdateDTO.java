package com.green.namuwiki.sessorActuator.dto;

import lombok.Data;

@Data
public class ThresholdUpdateDTO {
  private String crops;
  private double tempMin;
  private double tempMax;
  private double humidityMin;
  private double humidityMax;
  private double soilMin;
  private double soilMax;
  private double luxMin;
  private double luxMax;

}
