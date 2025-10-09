package com.example.CinemaBooking.service;

import com.example.CinemaBooking.model.dto.SeatDto;

import java.util.List;

public interface SeatService {
    List<SeatDto> findSeatsByRoom(Long roomId);
    SeatDto findSeatById(Long id);
}
