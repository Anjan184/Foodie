package com.foodie.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;
    private String userId;
    private String hotelId;
    private String deliveryId;

    @ElementCollection
    @CollectionTable(name = "orderItems", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItems> orderItems = new ArrayList<>();
    private long orderTotal;
    private String orderStatus;
    private LocalDateTime orderDate;

    private String deliveryAddress;


}
