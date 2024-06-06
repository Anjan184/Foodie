package com.foodie.DTO;

import com.foodie.entities.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelDTO {

    private String description;
    private List<Hotel> hotels = new ArrayList<>();
}
