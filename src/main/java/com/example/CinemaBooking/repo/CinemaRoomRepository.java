package com.example.CinemaBooking.repo;

import com.example.CinemaBooking.model.entities.CinemaRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {}
