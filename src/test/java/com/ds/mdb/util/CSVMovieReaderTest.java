package com.ds.mdb.util;

import com.ds.mdb.model.Movie;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVMovieReaderTest {

    @Test
    void getMoviesFromFile() throws ParseException {
        CSVMovieReader csvMovieReader = new CSVMovieReader();
        List<Movie> movieList = csvMovieReader.getMoviesFromFile("movies_test.csv");
        List<Movie> expectedList = generateMovies();

        assertEquals(movieList.size(), expectedList.size());

        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getName(), movieList.get(i).getName());
            assertEquals(expectedList.get(i).getGenre(), movieList.get(i).getGenre());
            assertEquals(expectedList.get(i).getLeadStudio(), movieList.get(i).getLeadStudio());
            assertEquals(expectedList.get(i).getAudienceScore(), movieList.get(i).getAudienceScore());
            assertEquals(expectedList.get(i).getProfitability(), movieList.get(i).getProfitability());
            assertEquals(expectedList.get(i).getRottenTomatoesScore(), movieList.get(i).getRottenTomatoesScore());
            assertEquals(expectedList.get(i).getWorldwideGross().getAmount(), movieList.get(i).getWorldwideGross().getAmount());
            assertEquals(expectedList.get(i).getWorldwideGross().getCurrency(), movieList.get(i).getWorldwideGross().getCurrency());
            assertEquals(expectedList.get(i).getYear(), movieList.get(i).getYear());
        }

    }

    List<Movie> generateMovies() throws ParseException {
        List<Movie> movies = new LinkedList<>();
        movies.add(new Movie(0L, "Zack and Miri Make a Porno", "Romance",
                "The Weinstein Company", 70, 1.747541667, 64,
                CurrencyConverter.currencyToWorldwideGross("$41.94"), 2008));
        movies.add(new Movie(0L, "Youth in Revolt", "Comedy",
                "The Weinstein Company", 52, 1.09, 68,
                CurrencyConverter.currencyToWorldwideGross("$19.62"), 2010));
        movies.add(new Movie(0L, "You Will Meet a Tall Dark Stranger", "Comedy",
                "Independent", 35, 1.211818182, 43,
                CurrencyConverter.currencyToWorldwideGross("$26.66"), 2010));
        movies.add(new Movie(0L, "When in Rome", "Comedy",
                "Disney", 44, 0, 15,
                CurrencyConverter.currencyToWorldwideGross("$43.04"), 2010));
        movies.add(new Movie(0L, "What Happens in Vegas", "Comedy",
                "Fox", 72, 6.267647029, 28,
                CurrencyConverter.currencyToWorldwideGross("$219.37"), 2008));
        movies.add(new Movie(0L, "Water For Elephants", "Drama",
                "20th Century Fox", 72, 3.081421053, 60,
                CurrencyConverter.currencyToWorldwideGross("$117.09"), 2011));

        return movies;
    }
}