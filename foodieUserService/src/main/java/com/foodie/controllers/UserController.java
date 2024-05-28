package com.foodie.controllers;

import com.foodie.DTO.UserDTO;
import com.foodie.entities.User;
import com.foodie.repositories.UserRepository;
import com.foodie.services.UserService;
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

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
       User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
       List<User> users= userService.getAllUsers();
       return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }

    @PutMapping(value="update/{userid}")
    public ResponseEntity<User> updateUser(@PathVariable("userid") String userid,@RequestBody UserDTO userDTO){
        User updatedUser =  userService.updateUser(userid,userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "delete/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable("userid") String userid){
        userService.deleteUser(userid);
        return ResponseEntity.ok("User Deleted Successfully");
    }








}
