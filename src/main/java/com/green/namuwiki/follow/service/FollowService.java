package com.green.namuwiki.follow.service;

import com.green.namuwiki.follow.dto.FollowDTO;
import com.green.namuwiki.follow.mapper.FollowMapper;
import com.green.namuwiki.global.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

  private final FollowMapper followMapper;
  private final SimpMessagingTemplate messagingTemplate;

  public void follow(FollowDTO dto){
    followMapper.follow(dto);
    // 팔로우 알림: 팔로우 당한 사람(farmerEmail)에게 전송
    messagingTemplate.convertAndSend(
      "/sub/notifications/" + dto.getFarmerEmail(),
      new NotificationDTO("FOLLOW", dto.getFollowerNickname() + "님이 팔로우했습니다.")
    );
  }

  public void unfollow(FollowDTO dto){
    followMapper.unfollow(dto);
  }

  public List<FollowDTO> getFollowList(String followerEmail){
    return followMapper.getFollowList(followerEmail);
  }

  public boolean checkFollow(FollowDTO dto){
    return followMapper.checkFollow(dto) > 0;
  }

  public List<FollowDTO> getFollowerList(String farmerEmail){
    return followMapper.getFollowerList(farmerEmail);
  }

}
