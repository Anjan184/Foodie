package com.foodie.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderId;
    private String userId;
    private String hotelId;
    private Hotel hotel;
    private String deliveryId;
    private long orderTotal;
    private String orderStatus;
    private LocalDateTime orderDate;

    private String deliveryAddress;

    //    private List<MenuItems> orderItems;
}
