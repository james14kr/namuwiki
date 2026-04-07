package com.green.namuwiki.plant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Schema(description = "식물 식별 요청")
public class PlantIdentifyReq {

    @NotNull
    @NotEmpty
    @Schema(description = "식물 사진 목록 (jpg, png, 여러 장 가능)")
    private List<MultipartFile> images;
}
