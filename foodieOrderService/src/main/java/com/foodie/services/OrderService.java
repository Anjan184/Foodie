package com.foodie.services;

import com.foodie.DTO.OrderDTO;
import com.foodie.entity.Order;

import java.util.List;


public interface OrderService {

    //create order
    Order createOrder(Order order);

    //getallOrders
    List<Order> getAllorders();

    //get single order
    Order getsingleOrder(String orderId);

    //get orders by userId
    List<Order> getOrdersByUserId(String userId);

    //get orders by restaurantId
    List<Order> getOrdersByHotelId(String hotelId);


    //delete order
    boolean deleteOrder(String orderId);

    //update order
    Order updateOrder(String orderId, OrderDTO orderDTO);



}
