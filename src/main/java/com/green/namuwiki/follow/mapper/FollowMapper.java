package com.green.namuwiki.follow.mapper;

import com.green.namuwiki.follow.dto.FollowDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {

  void follow(FollowDTO dto);
  void unfollow(FollowDTO dto);
  List<FollowDTO> getFollowList(String followerEmail);



}
