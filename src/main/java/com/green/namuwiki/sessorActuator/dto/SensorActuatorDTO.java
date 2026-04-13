package com.green.namuwiki.sessorActuator.dto; // 패키지명은 프로젝트에 맞게 수정

import lombok.Data;

@Data
public class SensorActuatorDTO {

  private String deviceId;           // 기기 ID
  private String crops;              // 농작물 이름 (CROP 테이블 JOIN)
  private String createDate;         // 데이터 수집 시각

  private double tempC;              // 온도 (°C)
  private double humidity;           // 습도 (%)
  private double soilMoistureValue;  // 토양수분
  private double ldrValue;           // 조도

  private int fanStatus;             // 팬 ON/OFF (1/0)
  private int ledStatus;             // LED ON/OFF (1/0)
  private int pumpStatus;            // 펌프 ON/OFF (1/0)

  private double tempMin;            // 온도 최솟값 (임계값)
  private double tempMax;            // 온도 최댓값 (임계값)
  private double soilMin;            // 토양수분 최솟값 (임계값)
  private double soilMax;            // 토양수분 최댓값 (임계값)
  private double luxMin;             // 조도 최솟값 (임계값)
  private double luxMax;             // 조도 최댓값 (임계값)
}
