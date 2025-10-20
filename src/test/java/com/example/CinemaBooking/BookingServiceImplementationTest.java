package com.example.CinemaBooking;

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
import com.example.CinemaBooking.service.implementation.BookingServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplementationTest {

    @Mock
    private ScreeningRepository screeningRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImplementation bookingService;


    private BookingRequestDto requestDto;
    private Screening screening;
    private User user;
    private Set<Seat> seats;

    @BeforeEach
    void setUp() {
        // Setup date comune pentru teste
        requestDto = new BookingRequestDto();
        requestDto.setScreeningId(1L);
        requestDto.setUserId(1L);
        requestDto.setSeatIds(Set.of(1L, 2L, 3L));

        screening = new Screening();
        screening.setId(1L);
        // Adaugă alte proprietăți necesare pentru Screening

        user = new User();
        user.setId(1L);
        // Adaugă alte proprietăți necesare pentru User

        seats = new HashSet<>();
        Seat seat1 = new Seat();
        seat1.setId(1L);
        Seat seat2 = new Seat();
        seat2.setId(2L);
        Seat seat3 = new Seat();
        seat3.setId(3L);
        seats.add(seat1);
        seats.add(seat2);
        seats.add(seat3);
    }

    @Test
    void whenValidBookingRequestWithNoConflicts_thenCreateBookingSuccessfully() {
        // Given
        when(screeningRepository.findById(requestDto.getScreeningId())).thenReturn(Optional.of(screening));
        when(userRepository.findById(requestDto.getUserId())).thenReturn(Optional.of(user));

        when(bookingRepository.findConflictBookings(
                eq(requestDto.getScreeningId()),
                eq(requestDto.getSeatIds()),
                anyList()
        )).thenReturn(Collections.emptyList());

        when(seatRepository.findAllById(eq(requestDto.getSeatIds()))).thenReturn(new ArrayList<>(seats));

        // Mock salvarea booking-ului
        Booking savedBooking = new Booking();
        savedBooking.setId(1L);
        savedBooking.setUser(user);
        savedBooking.setScreening(screening);
        savedBooking.setSeats(seats);
        savedBooking.setStatus(BookingStatus.ONHOLD);
        savedBooking.setCreatedAt(LocalDateTime.now());
        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

        // When
        BookingResponseDto response = bookingService.bookSeats(requestDto);

        // Then
        assertNotNull(response);
        assertEquals(BookingStatus.ONHOLD.name(), response.getStatus());
        verify(bookingRepository, times(1)).save(any(Booking.class));
        verify(screeningRepository, times(1)).findById(requestDto.getScreeningId());
        verify(userRepository, times(1)).findById(requestDto.getUserId());
        verify(seatRepository, times(1)).findAllById(requestDto.getSeatIds());
    }

    @Test
    void whenSeatsAreAlreadyBooked_thenThrowIllegalArgumentExceptionWithCorrectMessage() {
        // Given
        when(screeningRepository.findById(requestDto.getScreeningId())).thenReturn(Optional.of(screening));
        when(userRepository.findById(requestDto.getUserId())).thenReturn(Optional.of(user));

        // Simulăm existența unor rezervări conflictuale
        List<Booking> conflictingBookings = List.of(new Booking(), new Booking());
        when(bookingRepository.findConflictBookings(
                eq(requestDto.getScreeningId()),
                eq(requestDto.getSeatIds()),
                anyList()
        )).thenReturn(conflictingBookings);

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookingService.bookSeats(requestDto)
        );

        assertEquals("One or more seats are already taken", exception.getMessage());
        verify(bookingRepository, never()).save(any(Booking.class));
        verify(seatRepository, never()).findAllById(anySet());
    }




}
