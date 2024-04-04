package com.example.SocialMedia.Service.Impl;

import com.example.SocialMedia.Exception.UserException;
import com.example.SocialMedia.Model.DTO.CommentDTO;
import com.example.SocialMedia.Model.DTO.PostDTO;
import com.example.SocialMedia.Model.DTO.UserDTO;
import com.example.SocialMedia.Model.Post;
import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Repository.CommentRepository;
import com.example.SocialMedia.Repository.UserRepository;
import com.example.SocialMedia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;


    /*
     * Creates a new user in the database.
     * @throws UserException if the user details are invalid or if a user with the same email already exists.
     */
    @Transactional
    @Override
    public User saveUser(User user) {
        if (user == null)

            throw new UserException("Invalid users Details");

        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null)
            throw new UserException("User with email: " + user.getEmail() + " already exists");

        return userRepository.save(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));
        List<PostDTO> postDTOs = user.getPosts().stream().map(PostDTO::new).collect(Collectors.toList());
        return new UserDTO(user, postDTOs);
    }

    @Override
    public UserDTO getUserName(String name) {
        if (name == null)
            throw new UserException("Invalid user name");

        User user = userRepository.findByUsername(name);
        if (user == null)
            throw new UserException("User with name: " + name + " not found");

        List<PostDTO> postDTOs = user.getPosts().stream().map(PostDTO::new).collect(Collectors.toList());
        return new UserDTO(user, postDTOs);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserException("No users found");
        }

        List<UserDTO> userDTOs = users.stream()
                .map(user -> {
                    List<Post> userPosts = user.getPosts();
                    List<PostDTO> postDTOs = userPosts.stream().map(PostDTO::new).collect(Collectors.toList());
                    return new UserDTO(user, postDTOs);
                })
                .collect(Collectors.toList());

        return userDTOs;
    }


    @Override
    public User DeleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));
        userRepository.deleteById(id);
        return user;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (((Optional<?>) existingUserOptional).isPresent()) {
                User existingUser = existingUserOptional.get();


                existingUser.setUsername(updatedUser.getUsername() != null ? updatedUser.getUsername() : existingUser.getUsername());
                existingUser.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : existingUser.getEmail());

                userRepository.save(existingUser);


                return new UserDTO(existingUser.getUserId(), existingUser.getUsername(), existingUser.getEmail());
            } else {
                return null;
            }
        }
    }




