package com.example.CinemaBooking.service.implementation;

import com.example.CinemaBooking.model.entities.Movie;
import com.example.CinemaBooking.repo.MovieRepository;
import com.example.CinemaBooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MovieServiceImplementation implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findMovieByName() {
        return movieRepository.findByName();
    }
}
