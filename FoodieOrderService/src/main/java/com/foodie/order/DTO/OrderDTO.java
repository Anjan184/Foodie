package com.foodie.order.DTO;

import com.foodie.order.entity.OrderItems;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId;
    private String userId;
    private String hotelId;
    private String deliveryId;
    private List<OrderItems> orderItems;
    private double orderTotal;
    private String orderStatus;
    private LocalDateTime orderDate;
    private String deliveryAddress;

}
