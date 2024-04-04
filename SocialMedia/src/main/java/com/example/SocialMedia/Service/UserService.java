package com.example.SocialMedia.Service;

import com.example.SocialMedia.Model.DTO.UserDTO;
import com.example.SocialMedia.Model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public UserDTO getUserById(Long id);
    public UserDTO getUserName(String name);
    public List<UserDTO> getAllUsers();
    public User DeleteUserById(Long id);
    UserDTO updateUser(Long id, UserDTO updatedUser);
}
