package com.example.SocialMedia.Service;

import com.example.SocialMedia.Model.Comment;
import com.example.SocialMedia.Model.DTO.CommentDTO;

import java.util.List;

public interface CommentService {
    public CommentDTO saveComment(Comment comment, Long userId , Long postId);
    public Comment getCommentById(Long id);
    public Comment DeleteCommentById(Long id);
    public List<CommentDTO> getAllCommentsByUserName(String name);
    public List<Comment> getAllCommentsByPostId(Long id);
}