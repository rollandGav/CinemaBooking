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
    SeatRepository seatRepository;

    @Override
    public CinemaRoom createRoom(CinemaRoomDto dto) {
        CinemaRoom room = new CinemaRoom();
        room.setName(dto.getName());
        room.setRowsCount(dto.getRowsCount());
        room.setColsCount(dto.getColsCount());
        CinemaRoom savedRoom = cinemaRoomRepository.save(room);

        for(int row = 1; row <=dto.getRowsCount(); row ++) {
            for (int col = 1; col <= dto.getColsCount(); col++) {
                Seat seat = new Seat();
                seat.setRowNumber(row);
                seat.setColNumber(col);
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

    @Override
    public CinemaRoom findRoomById(Long id) {
        return cinemaRoomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }
}
