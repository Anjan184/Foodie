package com.foodie.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItems {

    private String menuId;
    private String itemName;
    private String itemPrice;
//    @ManyToOne
//    @JoinColumn(name = "hotel_id")
//    @JsonBackReference
//    private Hotel hotel;
}
