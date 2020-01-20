package com.ds.mdb.controller;

import com.ds.mdb.model.Movie;
import com.ds.mdb.service.MovieService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @PostMapping("/")
    public ResponseEntity<Movie> putNewMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.putNewMovie(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovieByID(@RequestBody Movie newMovie, @PathVariable Long id) {
        Movie movie = null;
        try {
            movie = movieService.editMovie(movieService.getMovieByID(id), newMovie);
        } catch (NoSuchElementException nse) {
            LOGGER.error(nse.getMessage());
        }

        if (movie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieByID(@PathVariable Long id) {
        Movie movie = movieService.getMovieByID(id);

        if (movie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovieByID(@PathVariable Long id) {
        if (movieService.getMovieByID(id) != null) {
            movieService.deleteMovieByID(id);
            return ResponseEntity.ok().body("Movie with ID: '" + id + "' - deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found movie with ID : " + id);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> getMovieByDetails(@PathVariable Long id, @RequestBody String[] elements) {
        try {
            Map<String, Object> result = movieService.getByIDWith(id, elements);

            if (result == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found movie with ID : " + id);
            } else {
                return ResponseEntity.ok(result);
            }
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().build();
        }
    }
}
