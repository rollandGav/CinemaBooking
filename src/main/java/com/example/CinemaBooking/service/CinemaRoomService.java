package com.example.CinemaBooking.service;

import com.example.CinemaBooking.model.dto.CinemaRoomDto;
import com.example.CinemaBooking.model.entities.CinemaRoom;

import java.util.List;

public interface CinemaRoomService {
    CinemaRoom createRoom(CinemaRoomDto dto);
    List<CinemaRoom> findAllRooms();
    CinemaRoom findRoomById(Long id);
}
