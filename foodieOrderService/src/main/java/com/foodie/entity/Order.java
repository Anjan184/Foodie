package com.foodie.entity;

import com.foodie.Helper.MenuItems;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;
    private String userId;
    private String hotelId;
    private String deliveryId;

    @ElementCollection
    @Column(name = "orderItems")
    private List<MenuItems> orderItems;
    private long orderTotal;
    private String orderStatus;
    private LocalDateTime orderDate;

    private String deliveryAddress;

    public Order(){
        this.orderItems = new ArrayList<>();
    }
}
