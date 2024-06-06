package com.foodie.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "foodie_users")
public class User {
    @Id
    @Column(name = "userid")
    private String userid;
    private String userEmail;
    private String userName;
    private String userRole;
    private String password;
    private String phoneNo;
    private String zipCode;
    private String city;
    private String profilePic;
    private boolean isActive;
    private String gender;

    @Transient
    private List<Order> orders = new ArrayList<>();
}
