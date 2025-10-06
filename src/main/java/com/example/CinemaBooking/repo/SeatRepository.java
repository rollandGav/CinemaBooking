package com.example.CinemaBooking.repo;

import com.example.CinemaBooking.model.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {}
