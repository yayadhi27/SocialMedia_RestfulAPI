package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}