package com.example.CinemaBooking.service;

import com.example.CinemaBooking.model.dto.UserDto;
import com.example.CinemaBooking.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserDto dto);
    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    User updateUser(Long id, UserDto dto);
    void deleteUser(Long id);
}
