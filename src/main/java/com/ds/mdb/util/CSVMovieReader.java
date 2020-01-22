package com.ds.mdb.util;

import com.ds.mdb.model.Movie;
import com.ds.mdb.model.WorldwideGross;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVMovieReader {

    public List<Movie> getMoviesFromFile(String fileName) {
        List<Movie> list;

        try (Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(fileName).toURI()));
             CSVReader csvReader = new CSVReader(reader)) {
            list = new LinkedList<>();
            String[] line;

            csvReader.readNext(); // skip header
            while ((line = csvReader.readNext()) != null) {
                list.add(parseLine(line));
            }

        } catch (URISyntaxException | IOException e) {
            return null;
        }

        return list;
    }

    private Movie parseLine(String... line) {
        // define defaults
        String name = "";
        String genre = "";
        String leadStudio = "";
        int audienceScore = 0;
        double profitability = 0d;
        int rottenTomatoesScore = 0;
        WorldwideGross worldwideGross = new WorldwideGross("USD", new BigDecimal(0));
        int year = 0;

        try {

            if (line.length > 0) name = line[0];
            if (line.length > 1) genre = line[1];
            if (line.length > 2) leadStudio = line[2];
            if (line.length > 3) audienceScore = Integer.parseInt(line[3]);
            if (line.length > 4) profitability = Double.parseDouble(line[4]);
            if (line.length > 5) rottenTomatoesScore = Integer.parseInt(line[5]);
            if (line.length > 6) {
                worldwideGross = CurrencyConverter.currencyToWorldwideGross(line[6]);
            }
            if (line.length > 7) year = Integer.parseInt(line[7]);

        } catch (Exception e) {
            // ignored
        }

        return new Movie(0L, name, genre, leadStudio, audienceScore, profitability, rottenTomatoesScore, worldwideGross, year);
    }

}
