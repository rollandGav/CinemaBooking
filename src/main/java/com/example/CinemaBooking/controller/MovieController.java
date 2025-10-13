package com.example.CinemaBooking.controller;

import com.example.CinemaBooking.model.dto.MovieDto;
import com.example.CinemaBooking.model.entities.Movie;
import com.example.CinemaBooking.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Operation(summary = "Get all movies")
    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies(){
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping("/{title}")
    public ResponseEntity<Optional<Movie>> findMovieByTitle(@PathVariable String title){
        return ResponseEntity.ok(movieService.findMovieByTitle(title));
    }

    @Operation(summary = "Api to create a movie")
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.createMovie(movieService.toEntity(movieDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.updateMovie(id, movieService.toEntity(movieDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
