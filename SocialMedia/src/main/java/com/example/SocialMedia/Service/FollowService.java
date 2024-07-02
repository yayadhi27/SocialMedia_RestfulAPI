package com.example.SocialMedia.Service;

import com.example.SocialMedia.Model.User;

import java.util.List;

public interface FollowService {
    void followUser(Long followerId, Long followingId);
    void unfollowUser(Long followerId, Long followingId);
    List<User> getFollowers(Long userId);
    List<User> getFollowing(Long userId);
}


