package com.example.CinemaBooking.controller;

import com.example.CinemaBooking.model.dto.MovieDto;
import com.example.CinemaBooking.model.entities.Movie;
import com.example.CinemaBooking.service.MovieService;
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

    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies(){
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping("/{title}")
    public ResponseEntity<Optional<Movie>> findMovieByName(@PathVariable String title){
        return ResponseEntity.ok(movieService.findMovieByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody MovieDto dto){

        return ResponseEntity.ok(movieService.createMovie(movieService.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id){
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieDto dto) {
        return ResponseEntity.ok(movieService.updateMovie(id, dto));
    }

}
