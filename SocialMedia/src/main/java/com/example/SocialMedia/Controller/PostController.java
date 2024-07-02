package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Model.DTO.PostWithCommentsDTO;
import com.example.SocialMedia.Model.Post;
import com.example.SocialMedia.Service.PostService;
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

    /**
     * Creates a new post for a given username.
     *
     * @param post The post information to be created.
     * @param username The username of the user creating the post.
     * @return A ResponseEntity containing the created post information with a HTTP status of CREATED.
     */
    @PostMapping("/{username}")
    public ResponseEntity<Post> createPostHandel(@RequestBody Post post, @PathVariable String username) {
        return new ResponseEntity<>(postService.savePost(post, username ), HttpStatus.CREATED);
    }

    /**
     * Retrieves all posts by a given user ID.
     *
     * @param userId The ID of the user whose posts are to be retrieved.
     * @return A ResponseEntity containing a list of posts with comments DTOs.
     */
    @GetMapping("userId/{userId}")
    public ResponseEntity<List<PostWithCommentsDTO>> getPostByIdHandel(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getAllPostsByUserId(userId));
    }

    /**
     * Retrieves all posts by a given username.
     *
     * @param username The username of the user whose posts are to be retrieved.
     * @return A ResponseEntity containing a list of posts with comments DTOs.
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostWithCommentsDTO>> getPostsByUserNameHandel(@PathVariable String username) {
        return ResponseEntity.ok(postService.getAllPostsByUserName(username));
    }

    /**
     * Deletes a post by its ID.
     *
     * @param id The ID of the post to be deleted.
     * @return A ResponseEntity containing the deleted post information.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePostByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(postService.DeletePostById(id));
    }
}