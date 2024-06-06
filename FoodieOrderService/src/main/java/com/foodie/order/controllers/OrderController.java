package com.foodie.order.controllers;

import com.foodie.order.DTO.OrderDTO;
import com.foodie.order.entity.Order;
import com.foodie.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //create order
    @PostMapping("/create")
    public ResponseEntity<Order> createOrders(@RequestBody Order order){
        return ResponseEntity.ok().body(orderService.createOrder(order));
    }

    //get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok().body(orderService.getAllorders());
    }

    //delete order
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable String orderId){
        boolean isdeleted = orderService.deleteOrder(orderId);

        if(isdeleted){
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.badRequest().body(false);
    }

    //update order
    @PutMapping("/update/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody OrderDTO orderDTO){
        Order order = orderService.updateOrder(orderId, orderDTO);
        return ResponseEntity.ok().body(order);
    }

    //get by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getByUserId(@PathVariable String userId){
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    //get by userId
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Order>> getByHotelId(@PathVariable String userId){
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }
}