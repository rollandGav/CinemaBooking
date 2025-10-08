package com.example.CinemaBooking.service;

import com.example.CinemaBooking.model.dto.MovieDto;
import com.example.CinemaBooking.model.entities.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    public List<Movie> findAllMovies();
    Optional<Movie> findMovieByTitle(String name);
    Movie createMovie(Movie movie);
    void delete(Long id);
    Movie updateMovie(Long id, MovieDto dto);

    public default Movie toEntity(MovieDto dto){
        Movie m = new Movie(); m.setTitle(dto.getTitle()); m.setReleaseYear(dto.getReleaseYear()); m.setRating(dto.getRating());
        return m;
    }
}
