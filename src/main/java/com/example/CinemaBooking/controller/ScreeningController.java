package com.example.CinemaBooking.controller;

import com.example.CinemaBooking.model.dto.ScreeningDto;
import com.example.CinemaBooking.model.entities.Screening;
import com.example.CinemaBooking.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/screening")
public class ScreeningController {
    @Autowired
    ScreeningService screeningService;

    @PostMapping
    public ResponseEntity<Screening> createScreening(@RequestBody ScreeningDto screeningDto){
        return ResponseEntity.ok(screeningService.createScreening(screeningDto));
    }

    @GetMapping
    public ResponseEntity<List<Screening>> findAllScreenings(){
        return ResponseEntity.ok(screeningService.findAllScreening());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Screening> findScreeningById(@PathVariable Long id){
        return ResponseEntity.ok(screeningService.findScreeningById(id));
    }
}
