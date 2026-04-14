package com.green.namuwiki.dm.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class ChatRoomDTO {
  private Long id;
  private String senderEmail;
  private String receiverEmail;
  private String senderNickname;
  private String receiverNickname;
  private String senderProfileImg;
  private String receiverProfileImg;
  private String lastMessage;
  private LocalDateTime createdAt;
  private int unreadCount;


}
