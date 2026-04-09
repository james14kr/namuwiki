package com.green.namuwiki.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

// 인증번호 생성
@Component
public class AuthCodeUtil {
  public String generateAuthCode() {
    return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
  }
}
