package com.ds.mdb.controller;

import com.ds.mdb.model.Movie;
import com.ds.mdb.service.MovieService;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/movies")
@Api(value = "Simplified movie search Engine")
public class MovieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @ApiOperation(value = "Add a movie", response = Movie.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successfully added movie"),
            @ApiResponse(code = 417, message = "Movie Update failed")
    })
    @PostMapping("/")
    public ResponseEntity<Movie> putNewMovie(
            @ApiParam(value = "Movie object to be stored in database")
            @RequestBody Movie movie) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(movieService.putNewMovie(movie));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Movie creation failed", e);
        }
    }

    @ApiOperation(value = "Update an existing movie by ID", response = Movie.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successfully updated movie"),
            @ApiResponse(code = 404, message = "Source Movie by given ID not found"),
            @ApiResponse(code = 417, message = "Movie Update failed")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovieByID(
            @ApiParam(value = "Update movie object")
            @RequestBody Movie newMovie,
            @ApiParam(value = "Movie ID to update movie object")
            @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(movieService.editMovie(id, newMovie));
        } catch (NoSuchElementException nse) {
            LOGGER.error(nse.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Source Movie by given ID not found", nse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Movie Update failed", e);
        }
    }

    @ApiOperation(value = "Get Movie by ID", response = Movie.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved movie"),
            @ApiResponse(code = 404, message = "Movie not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieByID(
            @ApiParam(value = "Movie ID from which Movie object will be retrieved")
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.getMovieByID(id));
        } catch (NoSuchElementException nse) {
            LOGGER.error(nse.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found.", nse);
        }
    }

    @ApiOperation(value = "Delete Movie by ID", response = AbstractMap.SimpleEntry.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully deleted movie"),
            @ApiResponse(code = 404, message = "Movie not found"),
            @ApiResponse(code = 417, message = "Movie Deletion failed")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovieByID(
            @ApiParam(value = "Movie ID from which Movie object will be deleted from database")
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(new AbstractMap.SimpleEntry<>("deleted", movieService.deleteMovieByID(id)));
        } catch (NoSuchElementException nse) {
            LOGGER.error(nse.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found.", nse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Movie Deletion failed", e);
        }
    }

    @ApiOperation(value = "Retrieve Movie fields by given ID and list of field names", response = Map.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved movie"),
            @ApiResponse(code = 404, message = "Movie not found"),
            @ApiResponse(code = 400, message = "Bad given values(s)")
    })
    @GetMapping("/{id}/{fieldNames}")
    public ResponseEntity<Object> getMovieByDetails(
            @ApiParam(value = "Movie ID from which Movie details will be retrieved")
            @PathVariable Long id,
            @ApiParam(value = "List with fields to be retrieved from Movie object")
            @PathVariable List<String> fieldNames) {
        try {
            return ResponseEntity.ok(movieService.getByIDWith(id, fieldNames));
        } catch (IllegalArgumentException iae) {
            LOGGER.error(iae.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad given argument(s)", iae);
        } catch (NoSuchElementException nse) {
            LOGGER.error(nse.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found.", nse);
        }
    }
}
