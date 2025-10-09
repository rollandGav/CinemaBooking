package com.example.CinemaBooking.service.implementation;

import com.example.CinemaBooking.model.dto.SeatDto;
import com.example.CinemaBooking.model.entities.Seat;
import com.example.CinemaBooking.repo.CinemaRoomRepository;
import com.example.CinemaBooking.repo.SeatRepository;
import com.example.CinemaBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatServiceImplementation implements SeatService {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;


    public List<SeatDto> findSeatsByRoom(Long roomId) {
        List<Seat> seats = seatRepository.findByCinemaRoomId(roomId);
        return seats.stream().map(this::toDto).collect(Collectors.toList());
    }


    public SeatDto findSeatById(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new RuntimeException("Seat not found"));
        return toDto(seat);
    }


    private SeatDto toDto(Seat seat) {
        SeatDto dto = new SeatDto();
        dto.setRowNumber(seat.getRowNumber());
        dto.setColNumber(seat.getColNumber());
        if (seat.getCinemaRoom() != null) {
            dto.setCinemaRoomId(seat.getCinemaRoom().getId());
            dto.setCinemaRoomName(seat.getCinemaRoom().getName());
        }
        return dto;
    }
}
