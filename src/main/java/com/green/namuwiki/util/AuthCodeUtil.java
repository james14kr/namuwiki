package com.green.namuwiki.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthCodeUtil {
  public String generateAuthCode() {
    return = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
  }
}
