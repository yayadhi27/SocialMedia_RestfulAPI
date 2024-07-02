package com.example.SocialMedia.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Follow")
public class Follow {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "follower_id")
        private User follower;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "following_id")
        private User following;


}

