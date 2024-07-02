package com.example.SocialMedia;

import com.example.SocialMedia.Model.Comment;
import com.example.SocialMedia.Model.DTO.CommentDTO;
import com.example.SocialMedia.Model.Post;
import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Repository.CommentRepository;
import com.example.SocialMedia.Repository.PostRepository;
import com.example.SocialMedia.Repository.UserRepository;
import com.example.SocialMedia.Service.Impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveComment_ValidComment_ShouldSaveComment() {
        // Mock data
        User user = new User();
        user.setUserId(1L);
        user.setUsername("testuser");

        Post post = new Post();
        post.setPostId(1L);
        post.setTitle("Test Post");

        Comment comment = new Comment();
        comment.setText("This is a test comment.");

        // Mock repository methods
        when(userRepository.findByUserId(1L)).thenReturn(user);
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // Call service method
        CommentDTO savedComment = commentService.saveComment(comment, 1L, 1L);

        // Verify
        assertEquals("This is a test comment.", savedComment.getText());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void getCommentById_ValidId_ShouldReturnComment() {
        // Mock data
        Comment comment = new Comment();
        comment.setCommentId(1L);
        comment.setText("Test comment");

        // Mock repository methods
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // Call service method
        Comment foundComment = commentService.getCommentById(1L);

        // Verify
        assertEquals("Test comment", foundComment.getText());
        verify(commentRepository, times(1)).findById(1L);
    }

    @Test
    void deleteCommentById_ValidId_ShouldDeleteComment() {
        // Mock data
        Comment comment = new Comment();
        comment.setCommentId(1L);

        // Mock repository methods
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // Call service method
        Comment deletedComment = commentService.DeleteCommentById(1L);

        // Verify
        assertEquals(comment.getCommentId(), deletedComment.getCommentId());
        verify(commentRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAllCommentsByUserName_ValidUsername_ShouldReturnComments() {
        // Mock data
        User user = new User();
        user.setUserId(1L);
        user.setUsername("testuser");

        Post post = new Post();
        post.setPostId(1L);
        post.setTitle("Test Post");

        Comment comment = new Comment();
        comment.setText("This is a test comment.");
        comment.setUser(user);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        post.setComments(comments);
        user.setPosts(List.of(post));

        // Mock repository methods
        when(userRepository.findByUsername("testuser")).thenReturn(user);
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        // Call service method
        List<CommentDTO> commentDTOs = commentService.getAllCommentsByUserName("testuser");

        // Verify
        assertEquals(1, commentDTOs.size());
        assertEquals("This is a test comment.", commentDTOs.get(0).getText());
        assertEquals("testuser", commentDTOs.get(0).getUsername());
    }


    @Test
    void getAllCommentsByPostId_ValidPostId_ShouldReturnComments() {
        // Mock data
        Post post = new Post();
        post.setPostId(1L);

        Comment comment = new Comment();
        comment.setText("This is a test comment.");
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        post.setComments(comments);

        // Mock repository methods
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        // Call service method
        List<Comment> foundComments = commentService.getAllCommentsByPostId(1L);

        // Verify
        assertEquals(1, foundComments.size());
        assertEquals("This is a test comment.", foundComments.get(0).getText());
    }
}
