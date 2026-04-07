package com.green.namuwiki.plant.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.namuwiki.plant.dto.KpniRes;
import com.green.namuwiki.plant.dto.PlantIdentifyReq;
import com.green.namuwiki.plant.dto.PlantIdentifyRes;
import com.green.namuwiki.plant.dto.PlantResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlantService {

    private static final int MAX_RESULTS = 3;
    private static final String PLANTNET_URL =
            "https://my-api.plantnet.org/v2/identify/all?nb-results=" + MAX_RESULTS + "&api-key=";

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final KpniService kpniService;

    @Value("${plantnet.api-key}")
    private String apiKey;

    public PlantIdentifyRes identify(PlantIdentifyReq req) {
        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            for (MultipartFile image : req.getImages()) {
                byte[] imageBytes = image.getBytes();
                String filename = image.getOriginalFilename();
                body.add("images", new ByteArrayResource(imageBytes) {
                    @Override
                    public String getFilename() {
                        return filename;
                    }
                });
                body.add("organs", "auto");
            }

            String response = restClient.post()
                    .uri(PLANTNET_URL + apiKey)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(body)
                    .retrieve()
                    .body(String.class);

            return parseResponse(response);

        } catch (Exception e) {
            log.error("PlantNet API 호출 실패: {}", e.getMessage());
            throw new RuntimeException("식물 식별에 실패했습니다.");
        }
    }

    private PlantIdentifyRes parseResponse(String response) throws Exception {
        JsonNode root = objectMapper.readTree(response);
        String bestMatch = root.path("bestMatch").asText();

        List<JsonNode> resultNodes = new ArrayList<>();
        for (JsonNode result : root.path("results")) {
            resultNodes.add(result);
            if (resultNodes.size() >= MAX_RESULTS) break;
        }

        List<CompletableFuture<PlantResult>> futures = new ArrayList<>();
        for (int i = 0; i < resultNodes.size(); i++) {
            int key = i + 1;
            JsonNode result = resultNodes.get(i);
            futures.add(CompletableFuture.supplyAsync(() -> {
                double score = result.path("score").asDouble();
                JsonNode species = result.path("species");

                String scientificName = species.path("scientificName").asText();
                String genus = species.path("genus").path("scientificNameWithoutAuthor").asText();
                String family = species.path("family").path("scientificNameWithoutAuthor").asText();

                List<String> commonNames = new ArrayList<>();
                for (JsonNode name : species.path("commonNames")) {
                    commonNames.add(name.asText());
                }

                KpniRes korInfo = kpniService.findKorInfo(scientificName);
                return new PlantResult(
                        key, score, scientificName,
                        genus, korInfo != null ? korInfo.getGenusKorNm() : null,
                        family, korInfo != null ? korInfo.getFalmKorNm() : null,
                        commonNames, korInfo != null ? korInfo.getPlantGnrlNm() : null
                );
            }));
        }

        List<PlantResult> results = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        int bestMatchKey = results.stream()
                .filter(r -> bestMatch.equals(r.getScientificName()))
                .mapToInt(PlantResult::getKey)
                .findFirst()
                .orElse(1);

        return new PlantIdentifyRes(bestMatch, bestMatchKey, results);
    }
}
