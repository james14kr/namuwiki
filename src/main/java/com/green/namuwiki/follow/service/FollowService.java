package com.green.namuwiki.follow.service;

import com.green.namuwiki.follow.dto.FollowDTO;
import com.green.namuwiki.follow.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

  private final FollowMapper followMapper;

  public void follow(FollowDTO dto){
    followMapper.follow(dto);
  }

  public void unfollow(FollowDTO dto){
    followMapper.unfollow(dto);
  }

  public List<FollowDTO> getFollowList(String followerEmail){
    return followMapper.getFollowList(followerEmail);
  }

}
