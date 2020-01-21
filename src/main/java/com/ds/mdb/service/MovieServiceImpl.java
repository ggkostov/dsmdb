package com.ds.mdb.service;

import com.ds.mdb.model.Movie;
import com.ds.mdb.repositories.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SequenceService sequenceService;

    @Override
    public Movie getMovieByID(Long id) throws NoSuchElementException {
        Optional<Movie> movie = movieRepository.findById(id);

        if (!movie.isPresent()) {
            throw new NoSuchElementException("Movie not found");
        }

        return movie.get();
    }

    @Override
    public Movie putNewMovie(Movie movie) {
        movie.setId(sequenceService.getNextSequence(Movie.SEQUENCE_NAME));
        return movieRepository.save(movie);
    }

    @Override
    public Movie editMovie(Long id, Movie newOne) {
        newOne.setId(getMovieByID(id).getId());
        return movieRepository.save(newOne);
    }

    @Override
    public boolean deleteMovieByID(Long id) {
        getMovieByID(id);

        movieRepository.deleteById(id);

        return true;
    }

    @Override
    public Map<String, Object> getByIDWith(Long id, List<String> fieldNames) throws IllegalArgumentException {
        Map<String, Object> result;

        if (fieldNames.size() <= 0) {
            throw new IllegalArgumentException("Missing arguments");
        } else {
            result = convertToMap(getMovieByID(id));

            for (String argument : fieldNames) {
                if (!result.containsKey(argument)) {
                    throw new IllegalArgumentException(String.format("Invalid argument '%s'", argument));
                }
            }

            result.keySet().retainAll(fieldNames);
        }

        return result;
    }

    private Map<String, Object> convertToMap(Movie movie) throws NoSuchElementException {
        Map<String, Object> result = null;

        if (movie != null) {
            result = new HashMap<>();
            result.put("id", movie.getId());
            result.put("name", movie.getName());
            result.put("genre", movie.getGenre());
            result.put("leadStudio", movie.getLeadStudio());
            result.put("audienceScore", movie.getAudienceScore());
            result.put("profitability", movie.getProfitability());
            result.put("rottenTomatoesScore", movie.getRottenTomatoesScore());
            result.put("worldwideGross", movie.getWorldwideGross());
            result.put("year", movie.getYear());
        }

        return result;
    }
}
