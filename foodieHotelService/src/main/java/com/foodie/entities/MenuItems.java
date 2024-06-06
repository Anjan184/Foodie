package com.foodie.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "foodie_menu_items")
public class MenuItems {

    @Id
    private String menuItemId;
    private String menuItemName;
    private String menuItemPrice;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;

    @Override
    public String toString() {
        return "MenuItems{" +
                "menuId='" + menuItemId + '\'' +
                ", itemName='" + menuItemName + '\'' +
                ", itemPrice='" + menuItemPrice + '\'' +
                '}';
    }
}
