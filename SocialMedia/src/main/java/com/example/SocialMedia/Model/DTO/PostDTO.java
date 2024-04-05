package com.example.SocialMedia.Model.DTO;

import com.example.SocialMedia.Model.Post;
import com.example.SocialMedia.Model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdOn;
    private Long userId;

    private List<CommentDTO> comment ;


    public PostDTO(Long postId, String title, String content, LocalDateTime createdOn) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
    }

    // Getters and setters (or Lombok annotations) for the fields


    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PostDTO(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdOn = post.getCreatedOn();
    }

    public PostDTO(Post post, List<CommentDTO> comment, User user) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdOn = post.getCreatedOn();
        this.userId = user.getUserId();
    }

    public PostDTO(Post post, List<CommentDTO> comment) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdOn = post.getCreatedOn();
        this.comment = comment;

    }
}