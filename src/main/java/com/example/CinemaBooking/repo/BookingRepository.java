package com.example.CinemaBooking.repo;

import com.example.CinemaBooking.model.entities.Booking;
import com.example.CinemaBooking.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // conflict query stays as before
    @Query("select b from Booking b join b.seats s where b.screening.id = :screeningId and s.id in :seatIds and b.status in :active")
    List<Booking> findConflictingBookings(@Param("screeningId") Long screeningId,
                                          @Param("seatIds") Set<Long> seatIds,
                                          @Param("active") List<BookingStatus> active);

    List<Booking> findByScreeningId(Long screeningId);

    // derived query: find bookings by status and createdAt before threshold
    List<Booking> findByStatusAndCreatedAtBefore(BookingStatus status, LocalDateTime threshold);

    // find bookings by user id
    List<Booking> findByUserId(Long userId);
}
