package com.example.SocialMedia.Model.DTO;

import com.example.SocialMedia.Model.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
    private Long commentId;
    @NotNull
    private String text;
    private LocalDateTime commentDatetime;
    private String username;

    public CommentDTO(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        this.text = comment.getText();
        this.username = comment.getUser() != null ? comment.getUser().getUsername() : "Unknown";
        this.commentDatetime = comment.getCommentDatetime();
    }

    public CommentDTO(){

    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCommentDatetime() {
        return commentDatetime;
    }

    public void setCommentDatetime(LocalDateTime commentDatetime) {
        this.commentDatetime = commentDatetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}