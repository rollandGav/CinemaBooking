package com.example.CinemaBooking.repo;

import com.example.CinemaBooking.model.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByCinemaRoomId(Long cinemaRoomId);
}
