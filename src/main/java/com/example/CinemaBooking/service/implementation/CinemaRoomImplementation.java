package com.example.CinemaBooking.service.implementation;

import com.example.CinemaBooking.model.dto.CinemaRoomDto;
import com.example.CinemaBooking.model.entities.CinemaRoom;
import com.example.CinemaBooking.model.entities.Seat;
import com.example.CinemaBooking.repo.CinemaRoomRepository;
import com.example.CinemaBooking.repo.SeatRepository;
import com.example.CinemaBooking.service.CinemaRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaRoomImplementation implements CinemaRoomService {
    @Autowired
    CinemaRoomRepository cinemaRoomRepository;
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public CinemaRoom createRoom(CinemaRoomDto dto) {
        CinemaRoom room = new CinemaRoom();
        room.setName(dto.getName());
        room.setRowsCount(dto.getRowsCount());
        room.setColsCount(dto.getColsCount());

        CinemaRoom savedRoom = cinemaRoomRepository.save(room);

        for (int r = 1; r <= dto.getRowsCount(); r++) {
            for (int c = 1; c <= dto.getColsCount(); c++) {
                Seat seat = new Seat();
                seat.setRowNumber(r);
                seat.setColNumber(c);
                seat.setCinemaRoom(savedRoom);
                seatRepository.save(seat);
            }
        }
        return savedRoom;
    }

    @Override
    public List<CinemaRoom> findAllRooms() {
        return cinemaRoomRepository.findAll();
    }


    public CinemaRoom findById(Long id) {
        return cinemaRoomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }
}
