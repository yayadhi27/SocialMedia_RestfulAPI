package com.example.SocialMedia.Service.Impl;

import com.example.SocialMedia.Exception.PostException;
import com.example.SocialMedia.Exception.UserException;
import com.example.SocialMedia.Model.DTO.CommentDTO;
import com.example.SocialMedia.Model.DTO.PostDTO;
import com.example.SocialMedia.Model.DTO.PostWithCommentsDTO;
import com.example.SocialMedia.Model.Post;
import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Repository.CommentRepository;
import com.example.SocialMedia.Repository.PostRepository;
import com.example.SocialMedia.Repository.UserRepository;
import com.example.SocialMedia.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Transactional
    @Override
    public Post savePost(Post post, String username) {
        if (post == null)
            throw new PostException("Invalid post Details");

        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UserException("User with name: " + username + " not found");

        post.setUser(user);
        post.setCreatedOn(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostException("Post not found"));
        List<CommentDTO> commentDTOs = post.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());
        return new PostDTO(post, commentDTOs);
    }

    @Override
    public Post DeletePostById(Long id) {
        if (id == null)
            throw new PostException("Invalid post id");

        Post post = postRepository.findById(id).orElseThrow(() -> new PostException("Post not found"));
        postRepository.deleteById(id);
        return post;
    }

    @Override
    public List<PostWithCommentsDTO> getAllPostsByUserName(String name) {
        if (name == null) {
            throw new PostException("Invalid user name");
        }

        User user = userRepository.findByUsername(name);
        if (user == null) {
            throw new PostException("User with name: " + name + " not found");
        }

        List<Post> posts = user.getPosts();
        List<PostWithCommentsDTO> postDTOs = posts.stream().map(PostWithCommentsDTO::new).collect(Collectors.toList());

        return postDTOs;
    }


    @Override
    public List<PostWithCommentsDTO> getAllPostsByUserId(Long userId) {
        if (userId == null) {
            throw new PostException("Invalid user name");
        }

        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new PostException("User with name: " + userId + " not found");
        }

        List<Post> posts = user.getPosts();
        List<PostWithCommentsDTO> postDTOs = posts.stream().map(PostWithCommentsDTO::new).collect(Collectors.toList());

        return postDTOs;
    }
}
