package com.green.namuwiki.dm.service;


import com.green.namuwiki.dm.dto.ChatMessageDTO;
import com.green.namuwiki.dm.dto.ChatRoomDTO;
import com.green.namuwiki.dm.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
  private final ChatMapper chatMapper;

  // 채팅방 생성/채팅방 조회
  public ChatRoomDTO getOrCreateRoom(String senderEmail, String receiverEmail) {
    ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
    chatRoomDTO.setSenderEmail(senderEmail);
    chatRoomDTO.setReceiverEmail(receiverEmail);

    // 기존 채팅방 유무
    ChatRoomDTO existRoom = chatMapper.findRoom(chatRoomDTO);
    if (existRoom == null) {
      chatMapper.createRoom(chatRoomDTO);
      existRoom = chatMapper.findRoom(chatRoomDTO);
    }
    return existRoom;
  }

  // 내 채팅방 목록 조회
  public List<ChatRoomDTO> getMyRooms(String memEmail) {
    return chatMapper.findMyRooms(memEmail);
  }

  // 메세지 저장
  public void saveMessage(ChatMessageDTO chatMessageDTO){
    chatMapper.saveMessage(chatMessageDTO);
  }

  // 채팅방 메세지 목록 조회
  public List<ChatMessageDTO> getMessage(long roomId){
    return chatMapper.findMessages(roomId);
  }

  // 메세지 읽음 처리
  public void readMessages(Long roomId, String memEmail){
    chatMapper.readMessages(roomId, memEmail);
  }





}
