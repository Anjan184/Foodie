package com.foodie.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.foodie.Helper.MenuItems;
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
@Table(name = "foodie_orders")
public class Order {

    @Id
    private String orderId;
    private String userId;
    private String hotelId;
    private String deliveryId;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItems> orderItems=new ArrayList<>();
    private long orderTotal;
    private String orderStatus;
    private LocalDateTime orderDate;
    private String deliveryAddress;

}
