package com.green.namuwiki.crop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CropDTO {

  private int cropId;
  private int farmId;
  private String cropName;
  private String cropDesc;
  private int cropPrice;

}
