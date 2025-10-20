package com.example.CinemaBooking.service.implementation;

import com.example.CinemaBooking.model.dto.BookingDto;
import com.example.CinemaBooking.model.dto.BookingRequestDto;
import com.example.CinemaBooking.model.dto.BookingResponseDto;
import com.example.CinemaBooking.model.entities.Booking;
import com.example.CinemaBooking.model.entities.Screening;
import com.example.CinemaBooking.model.entities.Seat;
import com.example.CinemaBooking.model.entities.User;
import com.example.CinemaBooking.model.enums.BookingStatus;
import com.example.CinemaBooking.repo.BookingRepository;
import com.example.CinemaBooking.repo.ScreeningRepository;
import com.example.CinemaBooking.repo.SeatRepository;
import com.example.CinemaBooking.repo.UserRepository;
import com.example.CinemaBooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImplementation implements BookingService {

    @Autowired
    ScreeningRepository screeningRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    BookingRepository bookingRepository;



    @Override
    public BookingResponseDto bookSeats(BookingRequestDto dto) {
        Screening screening = screeningRepository.findById(dto.getScreeningId()).orElseThrow(() -> new RuntimeException("Screening not found") );
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Set<Long> seatsIds = dto.getSeatIds();
        if(seatsIds == null || seatsIds.isEmpty()) throw new IllegalArgumentException("no seats requested");

        List<Booking> conflicts = bookingRepository.findConflictBookings(dto.getScreeningId(), seatsIds, List.of(BookingStatus.ONHOLD, BookingStatus.CONFIRMED));
        if(!conflicts.isEmpty()) throw new IllegalArgumentException("One or more seats are already taken");

        Set<Seat> seats = seatRepository.findAllById(seatsIds).stream().collect(Collectors.toSet());
        if (seats.size() != seatsIds.size()) throw new IllegalArgumentException("invalid seat ids");

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setScreening(screening);
        booking.setSeats(seats);
        booking.setStatus(BookingStatus.ONHOLD);
        booking.setCreatedAt(LocalDateTime.now());
        bookingRepository.save(booking);
        return toResponse(booking);
    }

    @Override
    public BookingResponseDto confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        if( booking.getStatus() == BookingStatus.CONFIRMED) return toResponse(booking);
        if( booking.getStatus() == BookingStatus.CANCELLED) return null;
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
        return toResponse(booking);
    }

    @Override
    public List<BookingDto> findBookingByUserId(Long userId) {
        return bookingRepository.findByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    private BookingDto toDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        if(booking.getUser() != null)  dto.setUserId(booking.getUser().getId());
        if (booking.getScreening() != null) dto.setScreeningId(booking.getScreening().getId());
        dto.setSeatsIds(booking.getSeats().stream().map(Seat::getId).collect(Collectors.toSet()));
        dto.setStatus(booking.getStatus().name());
        dto.setCreatedAt(booking.getCreatedAt());
        return dto;
    }

    public BookingResponseDto toResponse(Booking booking){
        BookingResponseDto dto = new BookingResponseDto();
        dto.setBookingId(booking.getId());
        dto.setStatus(booking.getStatus().name());
        dto.setCreatedAt(booking.getCreatedAt());
        return dto;
    }

//    @Scheduled(fixedRate = 60_000)
//    public void cleanUpBooking(){
//        LocalDateTime threshold = LocalDateTime.now().minusMinutes(ONHOLD_MINUTES);
//        List<Booking> toCancel = bookingRepository.findStatusOlderThan(threshold);
//    }
}
