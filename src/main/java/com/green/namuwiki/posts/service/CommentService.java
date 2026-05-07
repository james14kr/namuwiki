package com.green.namuwiki.posts.service;


import com.green.namuwiki.global.dto.NotificationDTO;
import com.green.namuwiki.posts.dto.CommentRequestDTO;
import com.green.namuwiki.posts.dto.CommentResponseDTO;
import com.green.namuwiki.posts.dto.PostResponseDTO;
import com.green.namuwiki.posts.mapper.CommentMapper;
import com.green.namuwiki.posts.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentMapper commentMapper;
  private final PostMapper postMapper;
  private final SimpMessagingTemplate messagingTemplate;

  // 댓글 등록 기능
  public void insertComment(CommentRequestDTO commentRequestDTO){
    commentMapper.insertComment(commentRequestDTO);
    // 댓글 알림: 게시글 작성자에게 전송 (본인 댓글 제외)
    PostResponseDTO post = postMapper.findById(commentRequestDTO.getPostId());
    if (post != null && !post.getMemEmail().equals(commentRequestDTO.getMemEmail())) {
      messagingTemplate.convertAndSend(
        "/sub/notifications/" + post.getMemEmail(),
        new NotificationDTO("COMMENT", "게시글에 새 댓글이 달렸습니다.")
      );
    }
  }

  // 댓글 조회 기능
  public List<CommentResponseDTO> selectComment(Long postId){
    return commentMapper.selectComment(postId);
  }

  // 댓글 수정 기능
  public void updateComment(CommentRequestDTO commentRequestDTO){
    commentMapper.updateComment(commentRequestDTO);
  }

  // 댓글 삭제 기능
  public void deleteComment(Long id){
    commentMapper.deleteComment(id);
  }

}
