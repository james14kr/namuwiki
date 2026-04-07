package com.green.namuwiki.plant.controller;

import com.green.namuwiki.plant.dto.KpniRes;
import com.green.namuwiki.plant.service.KpniService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Kpni", description = "국가식물표준목록 API")
@RestController
@RequestMapping("/kpni")
@RequiredArgsConstructor
public class KpniController {

    private final KpniService kpniService;

    @Operation(summary = "식물 학명 검색", description = "국가식물표준목록 scnmSearch 호출")
    @GetMapping("/search")
    public List<KpniRes> search(@RequestParam(defaultValue = "1") int pageNo,
                                @RequestParam(defaultValue = "10") int numOfRows,
                                @RequestParam String name) {
        return kpniService.search(pageNo, numOfRows, name);
    }
}
