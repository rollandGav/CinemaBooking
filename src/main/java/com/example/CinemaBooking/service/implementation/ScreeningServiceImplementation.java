package com.example.CinemaBooking.service.implementation;

import com.example.CinemaBooking.model.dto.ScreeningDto;
import com.example.CinemaBooking.model.entities.CinemaRoom;
import com.example.CinemaBooking.model.entities.Movie;
import com.example.CinemaBooking.model.entities.Screening;
import com.example.CinemaBooking.repo.CinemaRoomRepository;
import com.example.CinemaBooking.repo.MovieRepository;
import com.example.CinemaBooking.repo.ScreeningRepository;
import com.example.CinemaBooking.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningServiceImplementation implements ScreeningService {

    @Autowired
    ScreeningRepository screeningRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CinemaRoomRepository cinemaRoomRepository;


    @Override
    public Screening createScreening(ScreeningDto dto) {
        Movie movie = movieRepository.findById(dto.getMovieId()).orElseThrow(()-> new RuntimeException("Movie not found"));
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(dto.getCinemaRoomId()).orElseThrow(() -> new RuntimeException("Cinema room not found"));
        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setCinemaRoom(cinemaRoom);
        screening.setStartTime(dto.getStartTime());
        screening.setPrice(dto.getPrice());

        return screeningRepository.save(screening);
    }

    @Override
    public List<Screening> findAllScreening() {
        return screeningRepository.findAll();
    }

    @Override
    public Screening findScreeningById(Long id) {
        return screeningRepository.findById(id).orElseThrow(() -> new RuntimeException("Screening not found: " + id));
    }
}
