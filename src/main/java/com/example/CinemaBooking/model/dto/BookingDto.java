package com.example.CinemaBooking.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private Long userId;
    private Long screeningId;
    private Set<Long> seatsIds = new HashSet<>();
    private String status;
    private LocalDateTime createdAt;
}
