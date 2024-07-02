package com.example.SocialMedia;

import com.example.SocialMedia.Model.Follow;
import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Repository.FollowRepository;
import com.example.SocialMedia.Repository.UserRepository;
import com.example.SocialMedia.Service.Impl.FollowServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FollowServiceImplTest {

    @Mock
    private FollowRepository followRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FollowServiceImpl followService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void followUser_ValidFollowerAndFollowing_ShouldFollowUser() {
        User follower = new User();
        follower.setUserId(1L);

        User following = new User();
        following.setUserId(2L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(follower));
        when(userRepository.findById(2L)).thenReturn(Optional.of(following));
        when(followRepository.save(any(Follow.class))).thenReturn(new Follow());

        followService.followUser(1L, 2L);

        verify(followRepository, times(1)).save(any(Follow.class));
    }

    @Test
    void unfollowUser_ValidFollowerAndFollowing_ShouldUnfollowUser() {
        User follower = new User();
        follower.setUserId(1L);

        User following = new User();
        following.setUserId(2L);

        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);

        when(userRepository.findById(1L)).thenReturn(Optional.of(follower));
        when(userRepository.findById(2L)).thenReturn(Optional.of(following));
        when(followRepository.findByFollowerAndFollowing(follower, following)).thenReturn(Optional.of(follow));

        followService.unfollowUser(1L, 2L);

        verify(followRepository, times(1)).delete(follow);
    }

    @Test
    void getFollowers_ValidUserId_ShouldReturnFollowers() {
        User user = new User();
        user.setUserId(1L);

        User follower1 = new User();
        follower1.setUserId(2L);

        User follower2 = new User();
        follower2.setUserId(3L);

        Follow follow1 = new Follow();
        follow1.setFollower(follower1);
        follow1.setFollowing(user);

        Follow follow2 = new Follow();
        follow2.setFollower(follower2);
        follow2.setFollowing(user);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(followRepository.findByFollowing(user)).thenReturn(Arrays.asList(follow1, follow2));

        List<User> followers = followService.getFollowers(1L);

        assertEquals(2, followers.size());
        verify(followRepository, times(1)).findByFollowing(user);
    }

    @Test
    void getFollowing_ValidUserId_ShouldReturnFollowing() {
        User user = new User();
        user.setUserId(1L);

        User following1 = new User();
        following1.setUserId(2L);

        User following2 = new User();
        following2.setUserId(3L);

        Follow follow1 = new Follow();
        follow1.setFollower(user);
        follow1.setFollowing(following1);

        Follow follow2 = new Follow();
        follow2.setFollower(user);
        follow2.setFollowing(following2);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(followRepository.findByFollower(user)).thenReturn(Arrays.asList(follow1, follow2));

        List<User> following = followService.getFollowing(1L);

        assertEquals(2, following.size());
        verify(followRepository, times(1)).findByFollower(user);
    }
}

