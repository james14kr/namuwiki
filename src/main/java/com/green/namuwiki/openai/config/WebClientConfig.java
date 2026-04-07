package com.green.namuwiki.openai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  @Value("${openai.api-key}")
  private String apiKey;

  @Value("${openai.url}")
  private String apiUrl;

  @Bean
  public WebClient openAiWebClient() {
    return WebClient.builder()
            .baseUrl(apiUrl)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .build();
  }
}
