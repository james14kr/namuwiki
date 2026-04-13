package com.green.namuwiki.crop.controller;

import com.green.namuwiki.crop.dto.CropDTO;
import com.green.namuwiki.crop.service.CropService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/crop")
@RequiredArgsConstructor
public class CropController {

  private final CropService cropService;

  @PostMapping
  public ResponseEntity<Integer> registerCrop(@RequestBody CropDTO dto){
    cropService.registerCrop(dto);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<List<CropDTO>> getCropList(@RequestParam int farmId){
    return ResponseEntity.ok(cropService.getCropList(farmId));
  }

  //DElETE /crop/{cropId}
  //농장주가 자신의 농장에 등록된 농작물을 삭제할 때 호출
  @DeleteMapping("/{cropId}")
  public ResponseEntity<?> deleteCrop(@PathVariable int cropId){
    cropService.deleteCrop(cropId);
    return ResponseEntity.ok().build();
  }

  // GET /crop/my?farmerEmail=xxx@xxx.com
  //농장주가 기기 등록 시 연결할 농작물 목록 선택에 사용
  @GetMapping("/my")
  public ResponseEntity<List<CropDTO>> getMyCropList(@RequestParam String farmerEmail){
    return ResponseEntity.ok(cropService.getCropListByFarmerEmail(farmerEmail));
  }

}

