package com.example.CinemaBooking.controller;

import com.example.CinemaBooking.model.entities.Movie;
import com.example.CinemaBooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies(){
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Optional<Movie>> findMovieByName(@PathVariable String name){
        return ResponseEntity.ok(movieService.findMovieByName());
    }
}
