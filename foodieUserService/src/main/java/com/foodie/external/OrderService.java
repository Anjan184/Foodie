package com.foodie.external;

import com.foodie.Config.FeignClientConfiguration;
import com.foodie.entities.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient(name = "FOODIE-ORDER-SERVICE", configuration = FeignClientConfiguration.class)
public interface OrderService {

    @GetMapping("/orders/{orderId}")
    Order getSingleOrder(@PathVariable String orderId);

    @GetMapping("/orders")
    List<Order> getAllOrders();

    @GetMapping("/orders/user/{userId}")
    List<Order> getOrdersByUserId(@PathVariable String userId);

    @GetMapping("/orders/hotel/{hotelId}")
    List<Order> getOrdersByHotelId(@PathVariable String hotelId);

    @DeleteMapping("/orders/delete/{orderId}")
    Boolean deleteOrder(@PathVariable String orderId);


}
