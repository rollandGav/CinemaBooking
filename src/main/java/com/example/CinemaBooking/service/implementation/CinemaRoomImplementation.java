package com.example.CinemaBooking.service.implementation;

import com.example.CinemaBooking.model.dto.CinemaRoomDto;
import com.example.CinemaBooking.model.entities.CinemaRoom;
import com.example.CinemaBooking.repo.CinemaRoomRepository;
import com.example.CinemaBooking.service.CinemaRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaRoomImplementation implements CinemaRoomService {
    @Autowired
    CinemaRoomRepository cinemaRoomRepository;

    @Override
    public CinemaRoom createRoom(CinemaRoomDto dto) {
        CinemaRoom room = new CinemaRoom();
        room.setName(dto.getName());
        room.setRowsCount(dto.getRowsCount());
        room.setColsCount(dto.getColsCount());

        return cinemaRoomRepository.save(room);
    }
}
