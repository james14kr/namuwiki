package com.green.namuwiki.global.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @Validated @ModelAttribute 검증 실패
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationHandler(ConstraintViolationException e) {
        log.error("Validation failed: {}", e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    // @Validated @RequestBody 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        log.error("Validation failed: {}", e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    // 지원하지 않는 HTTP 메서드
    // GetMapping인데 post로 보내거나 그런거
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> methodNotSupportedHandler(HttpRequestMethodNotSupportedException e) {
        log.error("Method not supported: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    // 그 외 모든 예외는 500으로 고정
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> allExceptionHandler(Exception e) {
        log.error("Unexpected error: {}", e.getMessage());
        return ResponseEntity.internalServerError().build();
    }
}
