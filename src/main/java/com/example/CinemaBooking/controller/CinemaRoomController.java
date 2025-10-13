package com.example.CinemaBooking.controller;

import com.example.CinemaBooking.model.dto.CinemaRoomDto;
import com.example.CinemaBooking.model.entities.CinemaRoom;
import com.example.CinemaBooking.service.CinemaRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cinemaroom")
public class CinemaRoomController {
    @Autowired
    CinemaRoomService cinemaRoomService;

    @PostMapping
    public ResponseEntity<CinemaRoom> createRoom(@RequestBody CinemaRoomDto cinemaRoomDto){
        return ResponseEntity.ok(cinemaRoomService.createRoom(cinemaRoomDto));
    }

    @GetMapping
    public ResponseEntity<List<CinemaRoom>> findAllRooms(){
        return ResponseEntity.ok(cinemaRoomService.findAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CinemaRoom> findRoomById(@PathVariable Long id){
        return ResponseEntity.ok(cinemaRoomService.findRoomById(id));
    }


}
