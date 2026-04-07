package com.green.namuwiki.plant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlantResult {
    private int key;                  // 결과 식별 키
    private double score;             // 일치 확률
    private String scientificName;    // 학명
    private String genus;             // 속명 (영문)
    private String korGenusNm;        // 속명 (한글)
    private String family;            // 과명 (영문)
    private String korFamilyNm;       // 과명 (한글)
    private List<String> commonNames; // 일반명 (영문)
    private String korName;           // 일반명 (한글)
}
