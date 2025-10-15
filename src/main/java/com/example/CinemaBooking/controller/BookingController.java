package com.example.CinemaBooking.controller;

import com.example.CinemaBooking.model.dto.BookingDto;
import com.example.CinemaBooking.model.dto.BookingRequestDto;
import com.example.CinemaBooking.model.dto.BookingResponseDto;
import com.example.CinemaBooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto> bookSeat(@RequestBody BookingRequestDto dto){
        return ResponseEntity.ok(bookingService.bookSeats(dto));
    }

    @PostMapping("/confirm/{bookingId}")
    public ResponseEntity<BookingResponseDto> confirm (@PathVariable Long bookingId){
        return ResponseEntity.ok(bookingService.confirmBooking(bookingId));
    }

    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<Void> cancel(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<BookingDto>> findBookingByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(bookingService.findBookingByUserId(userId));
    }

}
