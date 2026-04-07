package com.green.namuwiki.openai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIStreamService {
  private final WebClient webClient;
  private final ObjectMapper objectMapper;

  private final String SYSTEM_PROMPT = String.join("\n",
          "너는 현재 고명한 식물 전문가야.",
          "우리가 하는 질문에 친절하게 답변해줘.",
          "답변은 바로 MDViewer에 보여줄 수 있게 md 양식으로 줘.",
          "이모지도 넣고, 사용자가 보기에 예쁜 양식으로 보여줘.",
          "절대 식물에 관한 질문 외에는 답변을 하면 안돼",
          "만약 사용자가 식물에 관련된 질문 외 다른걸 물어본다면, '해당 정보는 알려드릴 수 없습니다. 관리자에게 문의해주세요.' 라고 말해.",
          "답변에는 이미지를 절대 추가하지마.",
          "코드 블록으로 주지마. 무조건 마크다운 형식으로 줘",
          "현재 날짜는" + LocalDate.now(ZoneId.of("Asia/Seoul")) + " 이야."
  );

  public Flux<String> streamChat(String userMessage) {
    Map<String, Object> body = Map.of(
            "model", "gpt-4.1",
            "stream", true,
            "max_output_tokens", 1000,
            "instructions", SYSTEM_PROMPT,
            "input", new Object[]{
                    Map.of("role", "user", "content", userMessage)
            }
    );

    return webClient.post()
            .bodyValue(body)
            .retrieve()
            .bodyToFlux(String.class)
            .flatMap(chunk -> {
              String content = extractContent(chunk);

              return content != null
                      ? Flux.just(content)
                      : Flux.empty();
            });
  }

  private String extractContent(String chunk) {
    try {
      String trimmed = chunk.trim();

      if (trimmed.startsWith("data:")) {
        trimmed = trimmed.substring(5).trim();
      }

      if (trimmed.isEmpty()) return null;

      JsonNode root = objectMapper.readTree(trimmed);
      String type = root.path("type").asText("");

      if (!"response.output_text.delta".equals(type)) return null;

      return root.path("delta").asText();
    } catch (Exception e) {
      return null;
    }
  }
}
