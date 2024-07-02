package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Model.Follow;
import com.example.SocialMedia.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollower(User follower);
    List<Follow> findByFollowing(User following);
    Optional<Follow> findByFollowerAndFollowing(User follower, User following);
}

