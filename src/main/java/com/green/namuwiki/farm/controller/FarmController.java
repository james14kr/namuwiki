package com.green.namuwiki.farm.controller;

import com.green.namuwiki.farm.dto.FarmDTO;
import com.green.namuwiki.farm.service.FarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/farm")
public class FarmController {

  private final FarmService farmService;

  @PostMapping
  public ResponseEntity<?> registerFarm(@RequestBody FarmDTO dto){
    farmService.registerFarm(dto);
    return ResponseEntity.ok().build();
  }

}
