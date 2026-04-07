package com.green.namuwiki.plant.controller;

import com.green.namuwiki.plant.dto.PlantIdentifyReq;
import com.green.namuwiki.plant.dto.PlantIdentifyRes;
import com.green.namuwiki.plant.service.PlantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Plant", description = "식물 식별 API")
@RestController
@RequestMapping("/plant")
@RequiredArgsConstructor
public class PlantController {
    private final PlantService plantService;

    @Operation(summary = "식물 식별", description = "식물 사진을 업로드하면 PlantNet AI가 식물을 식별합니다.")
    @PostMapping(value = "/identify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PlantIdentifyRes identify(@Validated @ModelAttribute PlantIdentifyReq req) {
        return plantService.identify(req);
    }
}
