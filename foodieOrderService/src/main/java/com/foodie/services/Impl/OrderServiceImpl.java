package com.foodie.services.Impl;

import com.foodie.DTO.OrderDTO;
import com.foodie.entity.Order;
import com.foodie.repository.OrderRepository;
import com.foodie.services.OrderService;
import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    //create order
    @Override
    public Order createOrder(Order order) {
        String randomorderId = UUID.randomUUID().toString();
        order.setOrderId(randomorderId);
        order.setOrderDate(dateFormatting());
        return orderRepository.save(order);
    }

    //getting all orders
    @Override
    public List<Order> getAllorders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getsingleOrder(String orderId) {
        Optional<Order> or = orderRepository.findById(orderId);

        return or.orElse(null);
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return List.of();
    }

    @Override
    public List<Order> getOrdersByHotelId(String hotelId) {
        return List.of();
    }

    @Override
    public boolean deleteOrder(String orderId) {
        boolean done = false;
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) {
            Order delOrder = order.get();
            orderRepository.delete(delOrder);
            done = true;
        }
        return done;
    }

    @Transactional
    @Override
    public Order updateOrder(String orderId, OrderDTO orderDTO) {

        Optional<Order> oldorder = orderRepository.findById(orderId);
        if(oldorder.isPresent()){
            Order preorder = oldorder.get();
            modelMapper.map(orderDTO, preorder);
            return orderRepository.save(preorder);
        } else {
            throw new RuntimeException("Order with this id not found" + orderId);
        }
    }

    public LocalDateTime dateFormatting(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime current = LocalDateTime.now();
         String formatDate = formatter.format(current);
         return LocalDateTime.parse(formatDate,formatter);
    }
}
