package com.ds.mdb.service;

import com.ds.mdb.model.Movie;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public interface MovieService {

    Movie getMovieByID(Long id) throws NoSuchElementException;
    Movie putNewMovie(Movie movie);
    Movie editMovie(Long id, Movie newOne);
    boolean deleteMovieByID(Long id);
    Map<String, Object> getByIDWith(Long id, List<String> fieldNames) throws IllegalArgumentException;
}
