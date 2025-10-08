package com.example.CinemaBooking.service;

import com.example.CinemaBooking.model.dto.CinemaRoomDto;
import com.example.CinemaBooking.model.entities.CinemaRoom;

public interface CinemaRoomService {
    public CinemaRoom createRoom(CinemaRoomDto dto);
}
