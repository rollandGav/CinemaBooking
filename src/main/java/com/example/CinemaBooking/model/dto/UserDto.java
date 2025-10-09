package com.example.CinemaBooking.model.dto;

import com.example.CinemaBooking.model.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
    private Role role;
}
