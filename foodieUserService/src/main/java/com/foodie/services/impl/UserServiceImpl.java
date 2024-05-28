package com.foodie.services.impl;

import com.foodie.DTO.UserDTO;
import com.foodie.entities.User;
import com.foodie.repositories.UserRepository;
import com.foodie.services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserid(randomUserId);
       return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    @Override
    public void deleteUser(String userid) {
       Optional<User> user = userRepository.findUserById(userid);
        User existingUser=user.get();
        userRepository.delete(existingUser);
    }

    @Transactional
    @Override
    public User updateUser(String userid, UserDTO userDTO) {
        Optional<User> optionalUser=userRepository.findUserById(userid);
        if(optionalUser.isPresent()) {
            User existingUser=optionalUser.get();
            modelMapper.map(userDTO, existingUser);
           return userRepository.save(existingUser);
        }
        else {
            throw new RuntimeException("User with this id not found "+userid);
        }
    }



}
