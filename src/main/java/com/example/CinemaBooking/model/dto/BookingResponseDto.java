package com.example.CinemaBooking.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDto {
    private Long bookingId;
    private String status;
    private LocalDateTime createdAt;
}
