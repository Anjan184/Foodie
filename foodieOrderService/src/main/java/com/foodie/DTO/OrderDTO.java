package com.foodie.DTO;

import com.foodie.Helper.MenuItems;
import com.foodie.entity.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

//    private String hotelId;
//    private String deliveryId;
//    private List<OrderItems> orderItems;
    private double orderTotal;
    private String orderStatus;
    private LocalDateTime orderDate;
    private String deliveryAddress;

}
