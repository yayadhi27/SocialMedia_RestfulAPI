package com.example.SocialMedia;

import com.example.SocialMedia.Controller.CommentController;
import com.example.SocialMedia.Model.Comment;
import com.example.SocialMedia.Model.DTO.CommentDTO;
import com.example.SocialMedia.Service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCommentHandel_ShouldCreateComment() {
        Comment comment = new Comment();
        comment.setText("Test Comment");

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setText("Test Comment");

        when(commentService.saveComment(any(Comment.class), any(Long.class), any(Long.class))).thenReturn(commentDTO);

        ResponseEntity<CommentDTO> response = commentController.createCommentHandel(comment, 1L, 1L);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(commentDTO, response.getBody());
        verify(commentService, times(1)).saveComment(any(Comment.class), any(Long.class), any(Long.class));
    }

    @Test
    void getCommentByIdHandel_ShouldReturnComment() {
        Comment comment = new Comment();
        comment.setText("Test Comment");

        when(commentService.getCommentById(1L)).thenReturn(comment);

        ResponseEntity<Comment> response = commentController.getCommentByIdHandel(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comment, response.getBody());
        verify(commentService, times(1)).getCommentById(1L);
    }

    @Test
    void deleteCommentByIdHandel_ShouldDeleteComment() {
        Comment comment = new Comment();
        comment.setCommentId(1L);

        when(commentService.DeleteCommentById(1L)).thenReturn(comment);

        ResponseEntity<Comment> response = commentController.deleteCommentByIdHandel(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comment, response.getBody());
        verify(commentService, times(1)).DeleteCommentById(1L);
    }

    @Test
    void getCommentsByUserNameHandel_ShouldReturnCommentsForUsername() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setText("Test Comment");

        List<CommentDTO> commentDTOs = Arrays.asList(commentDTO);

        when(commentService.getAllCommentsByUserName("testuser")).thenReturn(commentDTOs);

        ResponseEntity<List<CommentDTO>> response = commentController.getCommentsByUserNameHandel("testuser");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDTOs, response.getBody());
        verify(commentService, times(1)).getAllCommentsByUserName("testuser");
    }

    @Test
    void getCommentsByPostIdHandel_ShouldReturnCommentsForPostId() {
        Comment comment = new Comment();
        comment.setText("Test Comment");

        List<Comment> comments = Arrays.asList(comment);

        when(commentService.getAllCommentsByPostId(1L)).thenReturn(comments);

        ResponseEntity<List<Comment>> response = commentController.getCommentsByPostIdHandel(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comments, response.getBody());
        verify(commentService, times(1)).getAllCommentsByPostId(1L);
    }
}

