package com.example.CinemaBooking.service;

import com.example.CinemaBooking.model.dto.BookingDto;
import com.example.CinemaBooking.model.dto.BookingRequestDto;
import com.example.CinemaBooking.model.dto.BookingResponseDto;

import java.util.List;

public interface BookingService {
    BookingResponseDto bookSeats(BookingRequestDto dto);
    BookingResponseDto confirmBooking(Long bookingId);
    List<BookingDto> findBookingByUserId(Long userId);
    void cancelBooking(Long bookingId);
}
