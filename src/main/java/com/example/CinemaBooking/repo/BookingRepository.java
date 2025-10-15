package com.example.CinemaBooking.repo;

import com.example.CinemaBooking.model.dto.BookingDto;
import com.example.CinemaBooking.model.entities.Booking;
import com.example.CinemaBooking.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select distinct b from Booking b join b.seats s where b.screening.id = :screeningId and s.id in :seatIds and b.status in :statuses")
    List<Booking> findConflictBookings(@Param("screeningId") Long screeningId,
                                       @Param("seatIds") Set<Long> seatIds,
                                       @Param("statuses") List<BookingStatus> statuses);

    List<Booking> findByUserId(Long userId);
}
