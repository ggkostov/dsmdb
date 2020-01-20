package com.ds.mdb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "movies")
public class Movie {

    public static final String SEQUENCE_NAME = "movies_sequence";

    @Id
    private long id;

    private String name;
    private String genre;
    private String leadStudio;
    private int audienceScore;
    private double profitability;
    private int rottenTomatoesScore;
    private BigDecimal worldwideGross;
    private int year;

    public Movie(long id, String name, String genre, String leadStudio, int audienceScore, double profitability,
                 int rottenTomatoesScore, BigDecimal worldwideGross, int year) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.leadStudio = leadStudio;
        this.audienceScore = audienceScore;
        this.profitability = profitability;
        this.rottenTomatoesScore = rottenTomatoesScore;
        this.worldwideGross = worldwideGross;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLeadStudio() {
        return leadStudio;
    }

    public void setLeadStudio(String leadStudio) {
        this.leadStudio = leadStudio;
    }

    public int getAudienceScore() {
        return audienceScore;
    }

    public void setAudienceScore(int audienceScore) {
        this.audienceScore = audienceScore;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public int getRottenTomatoesScore() {
        return rottenTomatoesScore;
    }

    public void setRottenTomatoesScore(int rottenTomatoesScore) {
        this.rottenTomatoesScore = rottenTomatoesScore;
    }

    public BigDecimal getWorldwideGross() {
        return worldwideGross;
    }

    public void setWorldwideGross(BigDecimal worldwideGross) {
        this.worldwideGross = worldwideGross;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
