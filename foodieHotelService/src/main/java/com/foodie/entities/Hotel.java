package com.foodie.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "foodie_hotels")
public class Hotel {
    @Id
    @JsonIgnore
    private String hotelId;
    private String hotelName;
    private String location;
    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<MenuItems> menuItems=new ArrayList<>();

    public Hotel(String hotelId) {
        this.hotelId = hotelId;
    }
}
