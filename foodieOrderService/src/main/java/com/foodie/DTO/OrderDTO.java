package com.foodie.DTO;

import com.foodie.Helper.MenuItems;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId;
    private String userId;
    private String hotelId;
    private String deliveryId;
    private List<MenuItems> orderItems;
    private double orderTotal;
    private String orderStatus;
    private LocalDateTime orderDate;
    private String deliveryAddress;

}
