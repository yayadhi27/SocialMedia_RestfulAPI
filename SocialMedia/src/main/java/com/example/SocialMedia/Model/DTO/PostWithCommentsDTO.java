package com.example.SocialMedia.Model.DTO;

import com.example.SocialMedia.Model.Post;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostWithCommentsDTO {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdOn;
    private List<CommentDTO> comments;

    public PostWithCommentsDTO(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdOn = post.getCreatedOn();

        // Map the comments using CommentDTO.
        this.comments = post.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());
    }

    public PostWithCommentsDTO() {
    }

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

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
