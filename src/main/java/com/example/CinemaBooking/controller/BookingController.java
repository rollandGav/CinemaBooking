package com.example.CinemaBooking.controller;

import com.example.CinemaBooking.model.dto.BookRequestDto;
import com.example.CinemaBooking.model.dto.BookingDto;
import com.example.CinemaBooking.model.dto.BookingResponseDto;
import com.example.CinemaBooking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService) { this.bookingService = bookingService; }

    @Operation(summary = "Create booking (hold)")
    @PostMapping("/screenings/{screeningId}")
    public ResponseEntity<BookingResponseDto> book(@PathVariable Long screeningId, @RequestBody BookRequestDto req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.bookSeats(screeningId, req));
    }

    @Operation(summary = "Confirm booking")
    @PostMapping("/{id}/confirm")
    public ResponseEntity<BookingResponseDto> confirm(@PathVariable Long id) { return ResponseEntity.ok(bookingService.confirmBooking(id)); }

    @Operation(summary = "Cancel booking")
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) { bookingService.cancelBooking(id); return ResponseEntity.noContent().build(); }

    @Operation(summary = "Get bookings by user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDto>> byUser(@PathVariable Long userId) { return ResponseEntity.ok(bookingService.findByUser(userId)); }
}