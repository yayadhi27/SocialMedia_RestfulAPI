package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Model.DTO.UserDTO;
import com.example.SocialMedia.Model.User;
import com.example.SocialMedia.Service.UserService;
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


    /**
     * Creates a new user.
     *
     * @param user The user information to be created.
     * @return A ResponseEntity containing the created user information with a HTTP status of CREATED.
     */
    @PostMapping("/newUser")
    public ResponseEntity<User> createUserHandel(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return A ResponseEntity containing the user information.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Retrieves all users.
     *
     * @return A ResponseEntity containing a list of all user DTOs.
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsersHandel() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return A ResponseEntity containing the deleted user information.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(userService.DeleteUserById(id));
    }

    /**
     * Retrieves a user by their username.
     *
     * @param name The username of the user to retrieve.
     * @return A ResponseEntity containing the user information.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<UserDTO> getUserByNameHandel(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserName(name));
    }

    /**
     * Updates a user by their ID.
     *
     * @param id The ID of the user to update.
     * @param updatedUser The updated user information.
     * @return A ResponseEntity containing the updated user information.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUser) {
        UserDTO userToUpdate = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(userToUpdate);
    }


}