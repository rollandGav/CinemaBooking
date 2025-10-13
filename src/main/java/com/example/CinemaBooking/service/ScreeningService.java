package com.example.CinemaBooking.service;

import com.example.CinemaBooking.model.dto.ScreeningDto;
import com.example.CinemaBooking.model.entities.Screening;

import java.util.List;

public interface ScreeningService {
    public Screening createScreening(ScreeningDto dto);
    public List<ScreeningDto> findAllScreening();
    public ScreeningDto findScreeningById(Long id);
}
