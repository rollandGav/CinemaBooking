package com.example.CinemaBooking.service.implementation;

import com.example.CinemaBooking.model.dto.BookRequestDto;
import com.example.CinemaBooking.model.dto.BookingDto;
import com.example.CinemaBooking.model.dto.BookingResponseDto;
import com.example.CinemaBooking.model.entities.*;
import com.example.CinemaBooking.repo.*;
import com.example.CinemaBooking.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.CinemaBooking.model.enums.BookingStatus;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingServiceImplementation implements BookingService {
    private final BookingRepository bookingRepo;
    private final ScreeningRepository screeningRepo;
    private final UserRepository userRepo;
    private final SeatRepository seatRepo;
    private static final long HOLD_MINUTES = 10;

    public BookingServiceImplementation(BookingRepository bookingRepo, ScreeningRepository screeningRepo, UserRepository userRepo, SeatRepository seatRepo) {
        this.bookingRepo = bookingRepo;
        this.screeningRepo = screeningRepo;
        this.userRepo = userRepo;
        this.seatRepo = seatRepo;
    }

    @Override
    @Transactional
    public BookingResponseDto bookSeats(Long screeningId, BookRequestDto req) {
        Screening screening = screeningRepo.findById(screeningId).orElseThrow(() -> new IllegalArgumentException("Screening not found"));
        User user = userRepo.findById(req.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Set<Long> seatIds = req.getSeatIds();
        if (seatIds == null || seatIds.isEmpty()) throw new IllegalArgumentException("No seats requested");

        List<Booking> conflicts = bookingRepo.findConflictingBookings(screeningId, seatIds, List.of(BookingStatus.ONHOLD, BookingStatus.CONFIRMED));
        if (!conflicts.isEmpty()) throw new IllegalStateException("One or more seats already booked/held");

        Set<Seat> seats = seatRepo.findAllById(seatIds).stream().collect(Collectors.toSet());
        if (seats.size() != seatIds.size()) throw new IllegalArgumentException("Invalid seat ids");

        Booking booking = new Booking();
        booking.setScreening(screening);
        booking.setUser(user);
        booking.setSeats(seats);
        booking.setStatus(BookingStatus.ONHOLD);
        Booking saved = bookingRepo.save(booking);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public BookingResponseDto confirmBooking(Long bookingId) {
        Booking b = bookingRepo.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        if (b.getStatus() == BookingStatus.CONFIRMED) return toResponse(b);
        b.setStatus(BookingStatus.CONFIRMED);
        Booking saved = bookingRepo.save(b);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking b = bookingRepo.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        b.setStatus(BookingStatus.CANCELLED);
        bookingRepo.save(b);
    }

    @Override
    public List<BookingDto> findByUser(Long userId) {
        return bookingRepo.findByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    private BookingDto toDto(Booking b) {
        BookingDto dto = new BookingDto();
        dto.setUserId(b.getUser() != null ? b.getUser().getId() : null);
        dto.setScreeningId(b.getScreening() != null ? b.getScreening().getId() : null);
        dto.setSeatIds(b.getSeats().stream().map(Seat::getId).collect(Collectors.toSet()));
        dto.setStatus(b.getStatus().name());
        dto.setCreatedAt(b.getCreatedAt());
        return dto;
    }

    private BookingResponseDto toResponse(Booking b) {
        BookingResponseDto r = new BookingResponseDto();
        r.setBookingId(b.getId());
        r.setStatus(b.getStatus().name());
        r.setCreatedAt(b.getCreatedAt());
        return r;
    }

    // scheduled cleanup
    @Scheduled(fixedRate = 60_000)
    @Transactional
    public void cleanupHeldBookings() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(HOLD_MINUTES);
        List<Booking> toCancel = bookingRepo.findByStatusAndCreatedAtBefore(BookingStatus.ONHOLD, threshold);
        for (Booking b : toCancel) {
            b.setStatus(BookingStatus.CANCELLED);
            bookingRepo.save(b);
        }
    }
}