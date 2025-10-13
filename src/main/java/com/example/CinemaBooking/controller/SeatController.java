package com.example.CinemaBooking.controller;

import com.example.CinemaBooking.model.dto.SeatDto;
import com.example.CinemaBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    SeatService service;

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<SeatDto>> findAllSeatsByRoom(@PathVariable Long roomId){
        return ResponseEntity.ok(service.findSeatByRoom(roomId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatDto> findSeatById(@PathVariable Long id){
        return ResponseEntity.ok(service.findSeatById(id));
    }
}
