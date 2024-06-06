package com.foodie.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Labels")
public class Label {

    @Id
    private String labelId;

    @Column(unique = true)
    private String description;

    @ManyToMany(mappedBy = "labels")
    @JsonIgnoreProperties("labels")
    private List<Hotel> hotels = new ArrayList<>();

}
