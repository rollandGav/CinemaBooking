package com.example.CinemaBooking.service;

import com.example.CinemaBooking.model.dto.UserDto;
import com.example.CinemaBooking.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserDto dto);
    User updateUser(Long id, UserDto dto);
    Optional<User> findById(Long id);
    List<User> findAll();
    void delete(Long id);
}