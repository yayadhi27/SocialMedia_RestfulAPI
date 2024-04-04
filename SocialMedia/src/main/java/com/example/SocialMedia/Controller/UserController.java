package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Service.UserService;
import com.example.SocialMedia.Model.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/newUser")
    public ResponseEntity<User> createUserHandel(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsersHandel() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(userService.DeleteUserById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserDTO> getUserByNameHandel(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUser) {
        UserDTO userToUpdate = userService.updateUser(id, updatedUser);
           return ResponseEntity.ok(userToUpdate);
    }


}