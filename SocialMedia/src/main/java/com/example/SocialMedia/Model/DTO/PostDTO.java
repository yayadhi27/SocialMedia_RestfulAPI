package com.example.SocialMedia.Model.DTO;

import com.example.SocialMedia.Model.Post;
import com.example.SocialMedia.Model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdOn;
    private Long userId;


    public PostDTO(Long postId, String title, String content, LocalDateTime createdOn) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
    }

    // Getters and setters (or Lombok annotations) for the fields

    public PostDTO(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdOn = post.getCreatedOn();
    }

    public PostDTO(Post post, List<CommentDTO> commentDTOs, User user) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdOn = post.getCreatedOn();
        this.userId = user.getUserId();
    }

    public PostDTO(Post post, List<CommentDTO> commentDTOs) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdOn = post.getCreatedOn();

    }
}