package com.example.CinemaBooking.model.dto;

import com.example.CinemaBooking.model.entities.Screening;
import com.example.CinemaBooking.model.entities.Seat;
import com.example.CinemaBooking.model.entities.User;
import com.example.CinemaBooking.model.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class BookingDto {
    private User user;
    private Screening screening;
    private Set<Seat> seats = new HashSet<>();
    private BookingStatus status = BookingStatus.ONHOLD;
    private LocalDateTime createdAt;
}
