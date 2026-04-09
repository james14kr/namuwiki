package com.green.namuwiki.follow.mapper;

import com.green.namuwiki.follow.dto.FollowDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {

  //팔로우
  void follow(FollowDTO dto);

  //언팔로우
  void unfollow(FollowDTO dto);

  //팔로우 목록 조회
  List<FollowDTO> getFollowList(String followerEmail);

  //팔로우 여부 확인
  int checkFollow(FollowDTO dto);


}
