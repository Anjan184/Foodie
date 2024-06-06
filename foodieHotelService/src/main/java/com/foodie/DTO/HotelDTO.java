package com.foodie.DTO;

import com.foodie.entities.Label;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    private List<Label> labels;
}
