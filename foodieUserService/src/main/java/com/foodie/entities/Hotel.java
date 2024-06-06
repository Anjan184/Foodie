package com.foodie.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private String hotelId;
    private String hotelName;
    private String location;
//    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL,orphanRemoval = true)
//    @JsonManagedReference
    private List<MenuItems> menuItems=new ArrayList<>();
}
