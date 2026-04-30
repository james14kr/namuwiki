package com.green.namuwiki.dm.mapper;

import com.green.namuwiki.dm.dto.ChatMessageDTO;
import com.green.namuwiki.dm.dto.ChatRoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface ChatMapper {

  // 채팅방 생성 쿼리 실행 메서드
  void createRoom(ChatRoomDTO chatRoomDTO);

  // 채팅방 조회 쿼리 실행 메서드
  ChatRoomDTO findRoom(ChatRoomDTO chatRoomDTO);

  // 채팅방 목록 조회  쿼리 실행 메서드
  List<ChatRoomDTO> findMyRooms(String memEmail);

  // 메세지 저장  쿼리 실행 메서드
  void saveMessage(ChatMessageDTO chatMessageDTO);

  // 채팅방 메세지 목록 조회 쿼리 실행 메서드
  List<ChatMessageDTO> findMessages(Long roomId);

  // 메세지 읽음 처리 쿼리 실행 메서드
  void readMessages(Long roomId, String memEmail);

  // 프로필 이미지 조회 쿼리 실행 메서드
  ChatMessageDTO getMemberInfo(String senderEmail);
}
