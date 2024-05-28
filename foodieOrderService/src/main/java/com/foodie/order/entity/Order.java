package com.foodie.order.entity;

import com.foodie.order.Helper.MenuItems;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
