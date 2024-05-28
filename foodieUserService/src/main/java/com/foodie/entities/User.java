package com.foodie.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
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
}
