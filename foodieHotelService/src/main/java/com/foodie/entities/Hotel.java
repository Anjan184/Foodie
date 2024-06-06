package com.foodie.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "foodie_hotels")
public class Hotel {

    @Id
    private String hotelId;
    @Column(unique = true)
    private String hotelName;
    private String location;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<MenuItems> menuItems = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "hotel_label",
            joinColumns = @JoinColumn(name = "hotelId"),
            inverseJoinColumns = @JoinColumn(name = "labelId")
    )
    @JsonIgnoreProperties("hotels")
    private List<Label> labels = new ArrayList<>();;




}
