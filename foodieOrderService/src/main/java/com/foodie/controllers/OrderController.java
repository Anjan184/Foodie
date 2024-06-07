package com.foodie.controllers;

import com.foodie.DTO.OrderDTO;
import com.foodie.entity.Order;
import com.foodie.services.OrderService;
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
    @DeleteMapping("delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderId){
        boolean isdeleted = orderService.deleteOrder(orderId);
        if(isdeleted){
            return ResponseEntity.ok("Order has been deleted");
        }
        return ResponseEntity.badRequest().body("Something went wrong !");
    }

    //update order
    @PutMapping("update/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody OrderDTO orderDTO){
        Order order = orderService.updateOrder(orderId, orderDTO);
        return ResponseEntity.ok().body(order);
    }
}
