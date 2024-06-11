package com.foodie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.DTO.UserDTO;
import com.foodie.entities.User;
import com.foodie.repositories.UserRepository;
import com.foodie.services.UserService;

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
    public ResponseEntity<String> deleteUser(@PathVariable("userid") String userid){
        boolean isdeleted = userService.deleteUser(userid);
        if(isdeleted){
            return ResponseEntity.ok("User Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.getSingleUser(userId));
    }








}
