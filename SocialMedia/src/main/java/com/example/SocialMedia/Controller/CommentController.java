package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Model.Comment;
import com.example.SocialMedia.Service.CommentService;
import com.example.SocialMedia.Model.DTO.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<CommentDTO> createCommentHandel(@RequestBody Comment comment, @PathVariable Long postId, @PathVariable Long userId) {
        return new ResponseEntity<>(commentService.saveComment(comment,userId, postId), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentByIdHandel(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteCommentByIdHandel(@PathVariable Long id) {
        Comment deletedComment = commentService.DeleteCommentById(id);
        return ResponseEntity.ok(deletedComment);

    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<CommentDTO>> getCommentsByUserNameHandel(@PathVariable String username) {
        List<CommentDTO> comments = commentService.getAllCommentsByUserName(username);
        return ResponseEntity.ok(comments);
    }


    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostIdHandel(@PathVariable Long postId) {
        List<Comment> comments = commentService.getAllCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

}

