package com.example.SocialMedia.Service.Impl;

import com.example.SocialMedia.Exception.CommentException;
import com.example.SocialMedia.Exception.PostException;
import com.example.SocialMedia.Exception.UserException;
import com.example.SocialMedia.Model.Comment;
import com.example.SocialMedia.Model.DTO.CommentDTO;
import com.example.SocialMedia.Model.Post;
import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Repository.CommentRepository;
import com.example.SocialMedia.Repository.PostRepository;
import com.example.SocialMedia.Repository.UserRepository;
import com.example.SocialMedia.Service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    @Override
    public CommentDTO saveComment(Comment comment, Long userId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException("Post not found"));
        if (comment == null)
            throw new CommentException("Invalid comment details");

        User user = userRepository.findByUserId(userId);
        if (user == null)
            throw new UserException("User with ID: " + userId + " not found");

        comment.setCommentDatetime(LocalDateTime.now());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);

        return new CommentDTO(comment);
    }


    @Override
    public Comment getCommentById(Long id) {
        if (id == null)
            throw new CommentException("Invalid comment id");
        return commentRepository.findById(id).orElseThrow(() -> new CommentException("Comment not found"));
    }


    @Override
    public Comment DeleteCommentById(Long id) {
        if (id == null)
            throw new CommentException("Invalid comment id");
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentException("Comment not found"));
        commentRepository.deleteById(id);
        return comment;
    }


    @Override
    public List<CommentDTO> getAllCommentsByUserName(String username) {
        if (username == null || username.isEmpty()) {
            throw new UserException("Invalid username");
        }

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserException("User with username: " + username + " not found");
        }


        Post post = postRepository.findById(user.getUserId()).orElseThrow(() -> new PostException("Post not found"));
        List<Comment> comments = post.getComments();


        log.info("Comments: {}", comments);

        if (comments.isEmpty()) {
            throw new CommentException("No comments found for user: " + username);
        }

        return comments.stream().map(CommentDTO::new).collect(Collectors.toList());
    }


    @Override
    public List<Comment> getAllCommentsByPostId(Long id) {
        if (id == null)
            throw new PostException("Invalid post id");

        Post post = postRepository.findById(id).orElseThrow(() -> new PostException("Post not found"));
        List<Comment> comments = post.getComments();
        if (comments.isEmpty())
            throw new CommentException("No comments found");
        return comments;
    }
}