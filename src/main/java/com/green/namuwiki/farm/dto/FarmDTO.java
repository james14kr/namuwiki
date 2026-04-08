package com.green.namuwiki.farm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FarmDTO {

  private int farmId;
  private String farmerEmail;
  private String farmName;
  private String farmAddr;
  private String farmDesc;

}
