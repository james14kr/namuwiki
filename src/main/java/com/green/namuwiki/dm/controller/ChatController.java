package com.green.namuwiki.dm.controller;

import com.green.namuwiki.dm.dto.ChatMessageDTO;
import com.green.namuwiki.dm.dto.ChatRoomDTO;
import com.green.namuwiki.dm.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/dm")
@RestController
public class ChatController {
  private final ChatService chatService;
  // spring websocket에서 제공하는 클래스
  // 서버에서 클라이언트로 메세지 밀어주는 도구
  // rest api와 차이점 :
  // 서버가 먼저 클라이언트에게 데이터를 보낼수 있어서 실시간 채팅 가능
  // (rest api는 클라이언트가 요청해야만 응답)
  private final SimpMessagingTemplate simpMessagingTemplate;

  // 채팅방 생성 or 조회 api
  // (post) localhost:8080/api/dm/room
  @PostMapping("/room")
  public ResponseEntity<?> getOrCreateRoom(@RequestBody ChatRoomDTO chatRoomDTO){
    try {
      ChatRoomDTO response = chatService.getOrCreateRoom(
          chatRoomDTO.getSenderEmail()
          , chatRoomDTO.getReceiverEmail()
      );
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e){
      log.error("채팅방 생성 or 조회 중 오료",e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 내 채팅방 목록 조회 api
  // (get) localhost:8080/api/dm/rooms?memEmail=user1
  @GetMapping("/rooms")
  public ResponseEntity<?> getMyRooms(@RequestParam("memEmail") String memEmail){
    try {
      List<ChatRoomDTO> response = chatService.getMyRooms(memEmail);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e){
      log.error("채팅방 목록 조호 ㅣ중 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 채팅방 메세지 목록 조회 api
  // (get) localhost:8080/api/dm/messages/7
  @GetMapping("/messages/{roomId}")
  public ResponseEntity<?> getMessages(
      @PathVariable("roomId") Long roomId
      , @RequestParam("memEmail") String memEmail
  ){
    try {
      chatService.readMessages(roomId,memEmail);
      List<ChatMessageDTO> response = chatService.getMessage(roomId);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e){
      log.error("채팅방 메세지 목록 조회 중 오류", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  //  user1이 "안녕!" 입력 후 전송
  //  → /pub/dm/message 로 전달
  //  → DB에 "안녕!" 저장
  //  → /sub/dm/room/1 구독자(user1, user2)에게 "안녕!" 전송
  //  → user2 화면에 실시간으로 "안녕!" 표


  // WebSocket 메세지 처리
  // 클라이언트가 /pub/dm/message로 메세지 보내면 실행
  // /pub : WebSocketConfig에서 설정한 prefix
  @MessageMapping("/dm/message")
  public void sendMessage(@Payload ChatMessageDTO chatMessageDTO){
    // @payload : rest api의 @RequestBody같은 역할,
    //            보낸 메세지 내용 ChatMessageDTO로 받아옴
    try {
      // 받은 메세지를 DB에 저장(그래야 나중에 채팅방 들어와도 이전 메세지 있음)
      chatService.saveMessage(chatMessageDTO);

      // 저장된 메시지 목록에서 마지막 메시지 가져오기 (닉네임, 프로필 포함)
      List<ChatMessageDTO> messages = chatService.getMessage(chatMessageDTO.getRoomId());
      ChatMessageDTO enriched = messages.get(messages.size() - 1);

      simpMessagingTemplate.convertAndSend("/sub/dm/room/"+chatMessageDTO.getRoomId(), enriched);
      // /sub/dm/room/1 (채팅방) 경로를 구독중인 클라이언트한테 메세지 전송
      // user1, user2 둘다 /sub/dm/room/1 구독중이면 둘다 실시간으로 메세지 받음
    }catch (Exception e){
      log.error("메세지 전송 중 오류",e);
    }
  }


















}
