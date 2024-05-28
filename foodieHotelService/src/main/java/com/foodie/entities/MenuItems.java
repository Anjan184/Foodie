package com.foodie.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "foodie_menu_items")
public class MenuItems {
    @Id
    private String menuId;
    private String itemName;
    private String itemPrice;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;
}
