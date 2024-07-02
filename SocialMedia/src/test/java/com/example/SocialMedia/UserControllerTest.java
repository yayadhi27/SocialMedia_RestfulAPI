package com.example.SocialMedia;

import com.example.SocialMedia.Model.DTO.UserDTO;
import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Controller.UserController;
import com.example.SocialMedia.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUserHandel_ShouldCreateUser() {

        //Mock Data
        User user = new User();
        user.setEmail("test@example.com");

        //Mock Repository Method
        when(userService.saveUser(any(User.class))).thenReturn(user);

        //Call Service Method
        ResponseEntity<User> response = userController.createUserHandel(user);

        //Verify
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    void getUserByIdHandel_ShouldReturnUser() {


        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        when(userService.getUserById(1L)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUserByIdHandel(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void getAllUsersHandel_ShouldReturnListOfUsers() {
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setUsername("user1");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setUsername("user2");

        List<UserDTO> userDTOs = Arrays.asList(userDTO1, userDTO2);

        when(userService.getAllUsers()).thenReturn(userDTOs);

        ResponseEntity<List<UserDTO>> response = userController.getAllUsersHandel();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTOs, response.getBody());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void deleteUserByIdHandel_ShouldDeleteUser() {
        User user = new User();
        user.setUserId(1L);

        when(userService.DeleteUserById(1L)).thenReturn(user);

        ResponseEntity<User> response = userController.deleteUserByIdHandel(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).DeleteUserById(1L);
    }

    @Test
    void getUserByNameHandel_ShouldReturnUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        when(userService.getUserName("testuser")).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUserByNameHandel("testuser");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).getUserName("testuser");
    }

    @Test
    void updateUser_ShouldUpdateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("updateduser");

        when(userService.updateUser(eq(1L), any(UserDTO.class))).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.updateUser(1L, userDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).updateUser(eq(1L), any(UserDTO.class));
    }
}
