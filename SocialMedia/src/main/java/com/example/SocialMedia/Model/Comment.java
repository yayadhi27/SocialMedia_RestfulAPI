package com.example.SocialMedia.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "text", nullable = false, length = 255)
    @NotNull
    private String text;

    @Column(name = "comment_datetime", nullable = false)
    @NotNull
    private LocalDateTime commentDatetime;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}