package com.foodie.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodie.DTO.UserDTO;
import com.foodie.entities.Hotel;
import com.foodie.entities.Order;
import com.foodie.entities.User;
import com.foodie.external.HotelService;
import com.foodie.external.OrderService;
import com.foodie.repositories.UserRepository;
import com.foodie.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private HotelService hotelService;
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserid(randomUserId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        List<User> listuser = userRepository.findAll();

        List<User> listofUsers = listuser.stream().peek(users -> {
            List<Order> orderList = orderService.getOrdersByUserId(users.getUserid())
                    .stream().peek(order -> {
                        Hotel hotel = hotelService.getSingleHotel(order.getHotelId());
                        order.setHotel(modelMapper.map(hotel, Hotel.class));
                    }).collect(Collectors.toList());
            users.setOrders(orderList);
        }).collect(Collectors.toList());

       return listofUsers;
    }

    @Override
    public boolean deleteUser(String userid) {
        boolean f =true;
       Optional<User> user = userRepository.findUserById(userid);
       if(user.isPresent()){
           User existingUser=user.get();

           List<Order> userorders = orderService.getOrdersByUserId(userid);


           if(userorders.isEmpty()){
               System.err.println("the list is empty");
           }else {
               userorders.forEach(order -> {

                   boolean isdeleted = orderService.deleteOrder(order.getOrderId());
                   if (isdeleted){
                       System.out.println("orders deleted");
                   } else {
                       System.err.println("Failed to delete Order with ID: "+order.getOrderId());
                   }
               });
           }

           userRepository.delete(existingUser);
           f=true;
       } else {
           System.err.println("User with ID: " + userid + "cannot be found");
       }
       return f;
    }

    @Transactional
    @Override
    public User updateUser(String userid, UserDTO userDTO) {
        Optional<User> optionalUser=userRepository.findUserById(userid);
        if(optionalUser.isPresent()) {
            User existingUser=optionalUser.get();
            modelMapper.map(userDTO, existingUser);
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
           return userRepository.save(existingUser);
        }
        else {
            throw new RuntimeException("User with this id not found "+userid);
        }
    }

    @Override
    public User getSingleUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));

        List<Order> userOrders = orderService.getOrdersByUserId(userId)
                .stream().peek(order -> {
                    Hotel hotel = hotelService.getSingleHotel(order.getHotelId());
                    order.setHotel(modelMapper.map(hotel, Hotel.class));
                }).collect(Collectors.toList());

        user.setOrders(userOrders);
        return user;
    }


}
