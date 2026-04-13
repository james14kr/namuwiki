package com.green.namuwiki.dm.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class ChatMessageDTO {
  private Long id;
  private Long roomId;
  private String senderEmail;
  private String senderNickname;
  private String content;
  private LocalDateTime createdAt;
  private boolean isRead;


}
