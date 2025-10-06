package com.example.CinemaBooking.model.dto;

import com.example.CinemaBooking.model.entities.CinemaRoom;
import lombok.Data;

@Data
public class SeatDto {
    private Integer rowNumber;
    private Integer colNumber;
    private CinemaRoom cinemaRoom;
}
