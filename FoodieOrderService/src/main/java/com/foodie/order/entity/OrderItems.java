package com.foodie.order.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class OrderItems {

    private String menuName;
    private String menuPrice;
}

