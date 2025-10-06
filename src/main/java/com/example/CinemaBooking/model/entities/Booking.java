package com.example.CinemaBooking.model.entities;

import com.example.CinemaBooking.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User user;
    private Screening screening;
    private Set<Seat> seats = new HashSet<>();

    private BookingStatus status = BookingStatus.ONHOLD;
    private LocalDateTime createdAt;
}
