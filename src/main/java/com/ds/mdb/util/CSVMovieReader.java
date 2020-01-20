package com.ds.mdb.util;

import com.ds.mdb.model.Movie;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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
        Movie movie = null;
        try {
            movie = new Movie(0L, line[0], line[1], line[2], Integer.parseInt(line[3]), Double.parseDouble(line[4]),
                    Integer.parseInt(line[5]), currencyToBigDecimal(line[6]), Integer.parseInt(line[7])
            );
        } catch (Exception e) {
            return null;
        }
        return movie;
    }

    private BigDecimal currencyToBigDecimal(String currency) throws ParseException {
        NumberFormat format = NumberFormat.getNumberInstance(Locale.US);

        if (format instanceof DecimalFormat) {
            ((DecimalFormat) format).setParseBigDecimal(true);
        }

        return (BigDecimal) format.parse(currency.replace("$", ""));

    }
}
