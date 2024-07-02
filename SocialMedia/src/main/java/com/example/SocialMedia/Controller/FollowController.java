package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {


    @Autowired
    private FollowService followService;


    @GetMapping("/{id}/followers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable Long id) {
        List<User> followers = followService.getFollowers(id);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{id}/following")
    public ResponseEntity<List<User>> getFollowing(@PathVariable Long id) {
        List<User> following = followService.getFollowing(id);
        return ResponseEntity.ok(following);
    }

    @PostMapping("/follow/{followerId}/{followingId}")
    public ResponseEntity<String> followUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        followService.followUser(followerId, followingId);
        return ResponseEntity.ok("User was followed");
    }

    @DeleteMapping("/unfollow/{followerId}/{followingId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        followService.unfollowUser(followerId, followingId);
        return ResponseEntity.ok("User was Unfollowed");
    }

}
