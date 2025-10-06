package com.example.CinemaBooking.model.dto;

import com.example.CinemaBooking.model.entities.CinemaRoom;
import com.example.CinemaBooking.model.entities.Movie;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScreeningDto {
    private Movie movie;
    private CinemaRoom cinemaRoom;
    private LocalDateTime startTime;
    private Double price;
}
