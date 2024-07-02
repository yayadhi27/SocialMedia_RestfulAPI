package com.example.SocialMedia.Service.Impl;

import com.example.SocialMedia.Model.Follow;
import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Repository.FollowRepository;
import com.example.SocialMedia.Repository.UserRepository;
import com.example.SocialMedia.Service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void followUser(Long followerId, Long followingId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException("Follower with id " + followerId + " not found"));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new IllegalArgumentException("User to follow with id " + followingId + " not found"));

        if (follower.equals(following)) {
            throw new IllegalArgumentException("User cannot follow themselves");
        }

        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);

        followRepository.save(follow);
    }

    @Override
    @Transactional
    public void unfollowUser(Long followerId, Long followingId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException("Follower with id " + followerId + " not found"));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new IllegalArgumentException("User to unfollow with id " + followingId + " not found"));

        Follow follow = followRepository.findByFollowerAndFollowing(follower, following)
                .orElseThrow(() -> new IllegalArgumentException("Not following user with id " + followingId));

        followRepository.delete(follow);
    }

    @Override
    public List<User> getFollowers(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
        List<Follow> followers = followRepository.findByFollowing(user);
        return followers.stream()
                .map(Follow::getFollower)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getFollowing(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
        List<Follow> following = followRepository.findByFollower(user);
        return following.stream()
                .map(Follow::getFollowing)
                .collect(Collectors.toList());
    }
}
