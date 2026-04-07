package com.green.namuwiki.openai.controller;

import com.green.namuwiki.openai.service.OpenAIStreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/openai")
public class AIStreamController {
  private final OpenAIStreamService service;

  @GetMapping("/stream")
  public SseEmitter stream(@RequestParam String message) {
    SseEmitter emitter = new SseEmitter(0L);
    service.streamChat(message)
            .doOnNext(content -> send(emitter, content))
            .doOnError(emitter::completeWithError)
            .doOnComplete(emitter::complete)
            .subscribe();
    return emitter;
  }

  private void send(SseEmitter emitter, String content) {
    try {
      emitter.send(SseEmitter.event().data(Map.of("content", content)));
    } catch (IOException e) {
      emitter.completeWithError(e);
    }
  }
}
