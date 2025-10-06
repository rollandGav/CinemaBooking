package com.example.CinemaBooking.service;

import com.example.CinemaBooking.model.entities.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MovieService {
    public List<Movie> findAllMovies();
    public Optional<Movie> findMovieByName();
}
