package com.example.CinemaBooking.repo;

import com.example.CinemaBooking.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
