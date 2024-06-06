package com.foodie.services;

import com.foodie.DTO.UserDTO;
import com.foodie.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    boolean deleteUser(String userId);
    User updateUser(String userId, UserDTO userDTO);
    User getSingleUser(String userId);

}
