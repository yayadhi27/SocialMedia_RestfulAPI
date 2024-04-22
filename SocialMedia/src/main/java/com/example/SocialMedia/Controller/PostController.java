package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Model.Post;
import com.example.SocialMedia.Service.PostService;
import com.example.SocialMedia.Model.DTO.PostDTO;
import com.example.SocialMedia.Model.DTO.PostWithCommentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{username}")
    public ResponseEntity<Post> createPostHandel(@RequestBody Post post, @PathVariable String username) {
        return new ResponseEntity<>(postService.savePost(post, username ), HttpStatus.CREATED);
    }


    @GetMapping("userId/{userId}")
    public ResponseEntity<List<PostWithCommentsDTO>> getPostByIdHandel(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getAllPostsByUserId(userId));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostWithCommentsDTO>> getPostsByUserNameHandel(@PathVariable String username) {
        return ResponseEntity.ok(postService.getAllPostsByUserName(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePostByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(postService.DeletePostById(id));
    }
}