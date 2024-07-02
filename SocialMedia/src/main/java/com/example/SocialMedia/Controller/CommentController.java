package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Model.Comment;
import com.example.SocialMedia.Model.DTO.CommentDTO;
import com.example.SocialMedia.Service.CommentService;
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

    /**
     * Creates a new comment for a given post and user.
     *
     * @param comment The comment information to be created.
     * @param postId The ID of the post being commented on.
     * @param userId The ID of the user creating the comment.
     * @return A ResponseEntity containing the created comment information with a HTTP status of CREATED.
     */
    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<CommentDTO> createCommentHandel(@RequestBody Comment comment, @PathVariable Long postId, @PathVariable Long userId) {
        return new ResponseEntity<>(commentService.saveComment(comment,userId, postId), HttpStatus.CREATED);
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id The ID of the comment to retrieve.
     * @return A ResponseEntity containing the comment information.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentByIdHandel(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id The ID of the comment to delete.
     * @return A ResponseEntity containing the deleted comment information.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteCommentByIdHandel(@PathVariable Long id) {
        Comment deletedComment = commentService.DeleteCommentById(id);
        return ResponseEntity.ok(deletedComment);

    }

    /**
     * Retrieves all comments by a given username.
     *
     * @param username The username of the user whose comments are to be retrieved.
     * @return A ResponseEntity containing a list of comment DTOs.
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<List<CommentDTO>> getCommentsByUserNameHandel(@PathVariable String username) {
        List<CommentDTO> comments = commentService.getAllCommentsByUserName(username);
        return ResponseEntity.ok(comments);
    }

    /**
     * Retrieves all comments for a given post ID.
     *
     * @param postId The ID of the post whose comments are to be retrieved.
     * @return A ResponseEntity containing a list of comments.
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostIdHandel(@PathVariable Long postId) {
        List<Comment> comments = commentService.getAllCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

}

