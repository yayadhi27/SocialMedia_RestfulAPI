package com.example.SocialMedia;

import com.example.SocialMedia.Controller.PostController;
import com.example.SocialMedia.Model.DTO.PostWithCommentsDTO;
import com.example.SocialMedia.Model.Post;
import com.example.SocialMedia.Service.PostService;
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

class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPostHandel_ShouldCreatePost() {

        // Mock data
        Post post = new Post();
        post.setTitle("Test Post");

        // Mock repository method
        when(postService.savePost(any(Post.class), any(String.class))).thenReturn(post);

        // Call service method
        ResponseEntity<Post> response = postController.createPostHandel(post, "testuser");

        // Verify
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(post, response.getBody());
        verify(postService, times(1)).savePost(any(Post.class), any(String.class));
    }

    @Test
    void getPostByIdHandel_ShouldReturnPostsForUserId() {
        PostWithCommentsDTO postDTO = new PostWithCommentsDTO();
        postDTO.setTitle("Test Post");

        List<PostWithCommentsDTO> postDTOs = Arrays.asList(postDTO);

        when(postService.getAllPostsByUserId(1L)).thenReturn(postDTOs);

        ResponseEntity<List<PostWithCommentsDTO>> response = postController.getPostByIdHandel(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDTOs, response.getBody());
        verify(postService, times(1)).getAllPostsByUserId(1L);
    }

    @Test
    void getPostsByUserNameHandel_ShouldReturnPostsForUsername() {
        PostWithCommentsDTO postDTO = new PostWithCommentsDTO();
        postDTO.setTitle("Test Post");

        List<PostWithCommentsDTO> postDTOs = Arrays.asList(postDTO);

        when(postService.getAllPostsByUserName("testuser")).thenReturn(postDTOs);

        ResponseEntity<List<PostWithCommentsDTO>> response = postController.getPostsByUserNameHandel("testuser");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDTOs, response.getBody());
        verify(postService, times(1)).getAllPostsByUserName("testuser");
    }

    @Test
    void deletePostByIdHandel_ShouldDeletePost() {
        Post post = new Post();
        post.setPostId(1L);

        when(postService.DeletePostById(1L)).thenReturn(post);

        ResponseEntity<Post> response = postController.deletePostByIdHandel(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(post, response.getBody());
        verify(postService, times(1)).DeletePostById(1L);
    }
}



