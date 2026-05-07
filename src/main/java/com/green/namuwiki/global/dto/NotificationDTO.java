package com.green.namuwiki.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class NotificationDTO {
    private String type;      // "FOLLOW" | "COMMENT" | "DM" | "SENSOR"
    private String message;
    private String createdAt;
    private boolean isRead;

    // 편의 생성자
    public NotificationDTO(String type, String message) {
        this.type = type;
        this.message = message;
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.isRead = false;
    }
}
