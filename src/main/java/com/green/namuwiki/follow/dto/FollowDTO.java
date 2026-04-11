package com.green.namuwiki.follow.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FollowDTO {

  private String followerEmail;
  private String farmerEmail;
  private String farmerNickname;
  private String followerNickname;
}
