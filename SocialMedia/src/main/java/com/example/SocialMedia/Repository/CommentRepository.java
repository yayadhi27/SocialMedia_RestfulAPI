package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Model.Comment;
import com.example.SocialMedia.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser(User user);
}
