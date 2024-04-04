package com.example.SocialMedia.Service;

import com.example.SocialMedia.Model.DTO.PostDTO;
import com.example.SocialMedia.Model.DTO.PostWithCommentsDTO;
import com.example.SocialMedia.Model.Post;

import java.util.List;

public interface PostService {
    public Post savePost(Post post, String username);
    public PostDTO getPostById(Long id);
    public Post DeletePostById(Long id);
    public List<PostWithCommentsDTO> getAllPostsByUserName(String name);
}
