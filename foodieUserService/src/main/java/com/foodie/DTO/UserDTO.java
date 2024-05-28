package com.foodie.DTO;

import lombok.Data;

@Data
public class UserDTO {
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
