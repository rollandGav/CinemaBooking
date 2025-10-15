package com.example.CinemaBooking.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BookingRequestDto {
    private Long screeningId;
    private Long userId;
    private Set<Long> seatIds;
}
