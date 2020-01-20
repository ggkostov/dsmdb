package com.ds.mdb.components;

import com.ds.mdb.model.Movie;
import com.ds.mdb.repositories.MovieRepository;
import com.ds.mdb.service.SequenceService;
import com.ds.mdb.util.CSVMovieReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class DataBaseSchema {

    @Value("${database.schema.file}")
    private String file;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SequenceService sequenceService;

    public void ensureSchema() {
        if (!isValid()) {
            insert();
        }
    }

    private boolean isValid() {
        boolean empty;

        try {
            empty = movieRepository.findAll().isEmpty();
        } catch (Exception e) {
            empty = true;
        }

        return !empty;
    }

    private void insert() {
        CSVMovieReader csvMovieReader = new CSVMovieReader();
        List<Movie> movies = csvMovieReader.getMoviesFromFile(file);

        movies.forEach(movie -> movie.setId(sequenceService.getNextSequence(Movie.SEQUENCE_NAME)));

        movieRepository.saveAll(movies);
    }
}
