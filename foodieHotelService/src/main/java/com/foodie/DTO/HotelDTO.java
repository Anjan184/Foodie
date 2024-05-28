package com.foodie.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    private String hotelId;
    private String hotelName;
    private String location;
    private List<MenuItemsDTO> menuItems;
}
