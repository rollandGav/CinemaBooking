package com.example.CinemaBooking.service.implementation;

import com.example.CinemaBooking.model.entities.Movie;
import com.example.CinemaBooking.repo.MovieRepository;
import com.example.CinemaBooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImplementation implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        Movie movieFound = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found: " + id));
        movieFound.setRating(movie.getRating());
        movieFound.setTitle(movie.getTitle());
        movieFound.setReleaseYear(movie.getReleaseYear());
        return movieRepository.save(movieFound);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
