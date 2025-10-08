package com.example.CinemaBooking.service;

import com.example.CinemaBooking.model.dto.MovieDto;
import com.example.CinemaBooking.model.entities.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    public List<Movie> findAllMovies();
    public Optional<Movie> findMovieByTitle(String title);

    public Movie createMovie(Movie movie);
    public Movie updateMovie(Long id, Movie movie);
    public void deleteMovie(Long id);

    public default Movie toEntity(MovieDto dto){
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setRating(dto.getRating());
        movie.setReleaseYear(dto.getReleaseYear());
        return movie;
    }
}
