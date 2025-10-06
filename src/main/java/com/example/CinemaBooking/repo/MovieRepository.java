package com.example.CinemaBooking.repo;

import com.example.CinemaBooking.model.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    public Optional<Movie> findByName();
}