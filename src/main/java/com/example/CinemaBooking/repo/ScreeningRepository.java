package com.example.CinemaBooking.repo;

import com.example.CinemaBooking.model.entities.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {}
