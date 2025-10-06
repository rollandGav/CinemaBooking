package com.example.CinemaBooking.repo;

import com.example.CinemaBooking.model.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {}
