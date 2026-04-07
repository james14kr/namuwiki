package com.green.namuwiki.plant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlantIdentifyRes {
    private String bestMatch;   // 최적 일치 학명
    private int bestMatchKey;   // 최적 일치 result의 key (UI 하이라이트용)
    private List<PlantResult> results;
}
