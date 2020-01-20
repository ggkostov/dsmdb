package com.ds.mdb.service;

import com.ds.mdb.model.Movie;
import com.ds.mdb.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public Movie editMovie(Movie oldOne, Movie newOne) {
        if (oldOne == null) return null;
        newOne.setId(oldOne.getId());
        return movieRepository.save(newOne);
    }

    @Override
    public void deleteMovieByID(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Map<String, Object> getByIDWith(Long id, String[] arguments) {
        Map<String, Object> result;

        if (arguments.length <= 0) {
            return null;
        } else {
            result = convertToMap(id);

            if (result == null) return null;

            for (String argument : arguments) {
                if (!result.containsKey(argument)) {
                    throw new IllegalArgumentException(String.format("Invalid argument '%s'", argument));
                }
            }

            result.keySet().retainAll(Arrays.asList(arguments));
        }

        return result;
    }

    private Map<String, Object> convertToMap(Long id) {
        Map<String, Object> result = null;

        Movie movie = getMovieByID(id);

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
//
//    String name;
//    String genre;
//    String leadStudio;
//    int audienceScore;
//    double profitability;
//    int rottenTomatoesScore;
//    BigDecimal worldwideGross;
//    int year;
}
