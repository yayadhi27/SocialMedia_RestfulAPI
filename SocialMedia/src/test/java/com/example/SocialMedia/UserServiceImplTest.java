package com.example.SocialMedia;

import com.example.SocialMedia.Exception.UserException;
import com.example.SocialMedia.Model.DTO.UserDTO;
import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Repository.CommentRepository;
import com.example.SocialMedia.Repository.UserRepository;
import com.example.SocialMedia.Service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_ShouldSaveUser_WhenUserIsValid() {

        // Mock Data
        User user = new User();
        user.setEmail("test@example.com");

        // Mock Repository Methods
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);

        // Call ServiceMethod
        User savedUser = userService.saveUser(user);

        //Verify
        assertNotNull(savedUser);
        assertEquals("test@example.com", savedUser.getEmail());
        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void saveUser_ShouldThrowException_WhenUserIsNull() {
        UserException exception = assertThrows(UserException.class, () -> userService.saveUser(null));
        assertEquals("Invalid users Details", exception.getMessage());
    }

    @Test
    void saveUser_ShouldThrowException_WhenUserAlreadyExists() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        UserException exception = assertThrows(UserException.class, () -> userService.saveUser(user));
        assertEquals("User with email: test@example.com already exists", exception.getMessage());
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("testuser");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO userDTO = userService.getUserById(1L);

        assertNotNull(userDTO);
        assertEquals("testuser", userDTO.getUsername());
    }

    @Test
    void getUserById_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserException exception = assertThrows(UserException.class, () -> userService.getUserById(1L));
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void getUserName_ShouldReturnUser_WhenUserExists() {
        User user = new User();
        user.setUsername("testuser");

        when(userRepository.findByUsername("testuser")).thenReturn(user);

        UserDTO userDTO = userService.getUserName("testuser");

        assertNotNull(userDTO);
        assertEquals("testuser", userDTO.getUsername());
    }

    @Test
    void getUserName_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.findByUsername("testuser")).thenReturn(null);

        UserException exception = assertThrows(UserException.class, () -> userService.getUserName("testuser"));
        assertEquals("User with name: testuser not found", exception.getMessage());
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers_WhenUsersExist() {
        User user = new User();
        user.setUsername("testuser");

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<UserDTO> users = userService.getAllUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals("testuser", users.get(0).getUsername());
    }

    @Test
    void getAllUsers_ShouldThrowException_WhenNoUsersFound() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        UserException exception = assertThrows(UserException.class, () -> userService.getAllUsers());
        assertEquals("No users found", exception.getMessage());
    }

    @Test
    void deleteUserById_ShouldDeleteUser_WhenUserExists() {
        User user = new User();
        user.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User deletedUser = userService.DeleteUserById(1L);

        assertNotNull(deletedUser);
        assertEquals(1L, deletedUser.getUserId());
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUserById_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserException exception = assertThrows(UserException.class, () -> userService.DeleteUserById(1L));
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void updateUser_ShouldUpdateUser_WhenUserExists() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("oldusername");
        user.setEmail("oldemail@example.com");

        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.setUsername("newusername");
        updatedUserDTO.setEmail("newemail@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO updatedUser = userService.updateUser(1L, updatedUserDTO);

        assertNotNull(updatedUser);
        assertEquals("newusername", updatedUser.getUsername());
        assertEquals("newemail@example.com", updatedUser.getEmail());
    }

    @Test
    void updateUser_ShouldReturnNull_WhenUserDoesNotExist() {
        UserDTO updatedUserDTO = new UserDTO();

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserDTO updatedUser = userService.updateUser(1L, updatedUserDTO);

        assertNull(updatedUser);
    }
}
