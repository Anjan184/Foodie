package com.foodie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItems {

    @Id
    private String menuItem;
    private String menuPrice;
    private String hotelId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @JsonBackReference
    private Order order;

}

