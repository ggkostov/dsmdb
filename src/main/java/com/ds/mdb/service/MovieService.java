package com.ds.mdb.service;

import com.ds.mdb.model.Movie;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public interface MovieService {

    Movie getMovieByID(Long id) throws NoSuchElementException;
    Movie putNewMovie(Movie movie);
    Movie editMovie(Movie oldOne, Movie newOne);
    void deleteMovieByID(Long id);
    List<Movie> findAll();
    Map<String, Object> getByIDWith(Long id, String[] arguments);
}
