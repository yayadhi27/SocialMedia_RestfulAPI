package com.example.SocialMedia;

import com.example.SocialMedia.Controller.FollowController;
import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Service.FollowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class FollowControllerTest {

    @Mock
    private FollowService followService;

    @InjectMocks
    private FollowController followController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(followController).build();
    }

    @Test
    void getFollowers_ValidUserId_ShouldReturnFollowers() throws Exception {
        User follower1 = new User();
        follower1.setUserId(2L);
        follower1.setUsername("user2");

        User follower2 = new User();
        follower2.setUserId(3L);
        follower2.setUsername("user3");

        List<User> followers = Arrays.asList(follower1, follower2);

        when(followService.getFollowers(1L)).thenReturn(followers);

        mockMvc.perform(get("/follow/1/followers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is("user2")))
                .andExpect(jsonPath("$[1].username", is("user3")));
    }

    @Test
    void getFollowing_ValidUserId_ShouldReturnFollowing() throws Exception {
        User following1 = new User();
        following1.setUserId(2L);
        following1.setUsername("user2");

        User following2 = new User();
        following2.setUserId(3L);
        following2.setUsername("user3");

        List<User> following = Arrays.asList(following1, following2);

        when(followService.getFollowing(1L)).thenReturn(following);

        mockMvc.perform(get("/follow/1/following"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is("user2")))
                .andExpect(jsonPath("$[1].username", is("user3")));
    }

    @Test
    void followUser_ValidIds_ShouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(post("/follow/follow/1/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("User was followed"));
    }

    @Test
    void unfollowUser_ValidIds_ShouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(delete("/follow/unfollow/1/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("User was Unfollowed"));
    }
}

