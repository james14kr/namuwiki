package com.green.namuwiki.member.service;


import com.green.namuwiki.global.config.aws.service.S3UploadService;
import com.green.namuwiki.member.dto.MemInfoDTO;
import com.green.namuwiki.member.mapper.MemInfoMapper;
import com.green.namuwiki.posts.dto.CommentResponseDTO;
import com.green.namuwiki.posts.dto.PostResponseDTO;
import com.green.namuwiki.posts.mapper.CommentMapper;
import com.green.namuwiki.posts.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemInfoService {
  private final MemInfoMapper memInfoMapper;
  private final S3UploadService service;
  private final PostMapper postMapper;
  private final CommentMapper commentMapper;


  // 마이페이지 정보 조회 기능
  public MemInfoDTO myInfo(String memEmail){
    return memInfoMapper.myInfo(memEmail);
  }

  // 마이페이지 사진 수정 기능
  public void updateProfileImg(MemInfoDTO memInfoDTO){
    memInfoMapper.updateProfileImg(memInfoDTO);
  }

  // 내가 작성한 게시글 조호 기능
  public List<PostResponseDTO> findMyPosts(String memEmail){
    return postMapper.findByMemEmail(memEmail);
  }

  // 내가 작성한 댓글 조회 기능
  public List<CommentResponseDTO> findMyComments(String memEmail){
    return commentMapper.findByMemEmail(memEmail);
  }

}
