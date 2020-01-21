package com.ds.mdb.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "movies")
@ApiModel(description = "All Movie details.")
public class Movie {

    public static final String SEQUENCE_NAME = "movies_sequence";

    @Id
    @ApiModelProperty(notes = "Movie database identifier")
    private long id;

    @ApiModelProperty(notes = "Movie name")
    private String name;

    @ApiModelProperty(notes = "Movie genre")
    private String genre;

    @ApiModelProperty(notes = "Movie Lead Studio")
    private String leadStudio;

    @ApiModelProperty(notes = "Movie Score")
    private int audienceScore;

    @ApiModelProperty(notes = "Movie Profitability")
    private double profitability;

    @ApiModelProperty(notes = "Movie Score of Rotten Tomatoes")
    private int rottenTomatoesScore;

    @ApiModelProperty(notes = "Movie Worldwide Gross")
    private WorldwideGross worldwideGross;

    @ApiModelProperty(notes = "Movie Year")
    private int year;

    public Movie(long id, String name, String genre, String leadStudio, int audienceScore, double profitability,
                 int rottenTomatoesScore, WorldwideGross worldwideGross, int year) {
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

    public WorldwideGross getWorldwideGross() {
        return worldwideGross;
    }

    public void setWorldwideGross(WorldwideGross worldwideGross) {
        this.worldwideGross = worldwideGross;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
