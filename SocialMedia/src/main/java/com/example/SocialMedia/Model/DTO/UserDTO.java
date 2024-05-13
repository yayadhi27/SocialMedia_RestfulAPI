package com.example.SocialMedia.Model.DTO;

import com.example.SocialMedia.Model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private Integer age;
    private String address;
    private List<PostDTO> posts;

    public UserDTO(Long userId, String username, String email,Integer age, String address, List<PostDTO> posts) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.age = age;
        this.address = address;
        this.posts = posts;
    }

    public UserDTO(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public UserDTO(User user, List<PostDTO> posts) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.address =user.getAddress();
        this.posts = posts;
    }
}
