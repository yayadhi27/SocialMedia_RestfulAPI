package com.example.SocialMedia.Model.DTO;

import com.example.SocialMedia.Model.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
    private Long commentId;
    @NotNull
    private String text;
    private LocalDateTime commentDatetime;
    private String username;

    public CommentDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.text = comment.getText();
        this.commentDatetime = comment.getCommentDatetime();
        this.username = comment.getUser().getUsername();
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