package com.example.CinemaBooking.service;

import com.example.CinemaBooking.model.dto.BookRequestDto;
import com.example.CinemaBooking.model.dto.BookingDto;
import com.example.CinemaBooking.model.dto.BookingResponseDto;

import java.util.List;

public interface BookingService {
    BookingResponseDto bookSeats(Long screeningId, BookRequestDto req);
    BookingResponseDto confirmBooking(Long bookingId);
    void cancelBooking(Long bookingId);
    List<BookingDto> findByUser(Long userId);
}
