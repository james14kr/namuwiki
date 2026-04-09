package com.green.namuwiki.farm.controller;

import com.green.namuwiki.farm.dto.FarmDTO;
import com.green.namuwiki.farm.service.FarmService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @GetMapping
  public ResponseEntity<List<FarmDTO>> getFarmList(){
    return ResponseEntity.ok(farmService.getFarmList());
  }

  @GetMapping("/{farmId}")
  public ResponseEntity<FarmDTO> getFarmById(@PathVariable int farmId){
    return ResponseEntity.ok(farmService.getFarmById(farmId));
  }

}
