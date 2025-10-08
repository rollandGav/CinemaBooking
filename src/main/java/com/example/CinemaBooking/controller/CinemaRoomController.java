package com.example.CinemaBooking.controller;

import com.example.CinemaBooking.model.dto.CinemaRoomDto;
import com.example.CinemaBooking.model.entities.CinemaRoom;
import com.example.CinemaBooking.service.CinemaRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cinemaroom")
public class CinemaRoomController {
    @Autowired
    CinemaRoomService cinemaRoomService;

    @PostMapping
    public ResponseEntity<CinemaRoom> createRoom(@RequestBody CinemaRoomDto cinemaRoomDto){
        return ResponseEntity.ok(cinemaRoomService.createRoom(cinemaRoomDto));
    }
}
