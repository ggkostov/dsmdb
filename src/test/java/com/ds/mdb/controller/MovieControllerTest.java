package com.ds.mdb.controller;

import com.ds.mdb.AbstractWebApplicationContextTest;
import com.ds.mdb.model.Movie;
import com.ds.mdb.model.WorldwideGross;
import com.ds.mdb.service.SequenceService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.springframework.test.util.AssertionErrors.*;

public class MovieControllerTest extends AbstractWebApplicationContextTest {

    private static final String MOVIE_CONTROLLER_RESOURCE_PATH = "/movies/";

    @Autowired
    private SequenceService sequenceService;

    @Override
    @Before
    public void setMockMvc() {
        super.setMockMvc();
    }

    @Test
    public void putNewMovie() throws Exception {
        Movie movie = new Movie(0L, "UNIT_TEST_MOVIE", "TEST_GENRE", "UNIT_STUDIO",
                26, 9.88811654d, 15,
                new WorldwideGross("USD", new BigDecimal("52.33")), 2020
        );

        String jsonMovieObject = super.toJson(movie);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(MOVIE_CONTROLLER_RESOURCE_PATH)
                        .contentType(MediaType.APPLICATION_JSON).content(jsonMovieObject)
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("HTTP status", 201, status);

        String content = mvcResult.getResponse().getContentAsString();

        movie.setId(sequenceService.getLastSequence(Movie.SEQUENCE_NAME)); // get last sequence ID, however this is the last created ID for this object.

        assertEquals("Response Content", toJson(movie), content);
    }

    @Test
    public void updateMovieByID() throws Exception {
        Long moviesCount = sequenceService.getLastSequence(Movie.SEQUENCE_NAME);
        long movieIDToUpdate = new Random().nextInt(moviesCount.intValue() + 1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(MOVIE_CONTROLLER_RESOURCE_PATH + movieIDToUpdate)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        Movie movieToUpdate = fromJson(mvcResult.getResponse().getContentAsString(), Movie.class);
        movieToUpdate.setName("UNIT_TEST");

        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders
                .put(MOVIE_CONTROLLER_RESOURCE_PATH + movieIDToUpdate)
                .contentType(MediaType.APPLICATION_JSON).content(toJson(movieToUpdate))
        ).andReturn();

        int status = mvcResult2.getResponse().getStatus();
        assertEquals("HTTP status", 201, status);

        String content = mvcResult2.getResponse().getContentAsString();
        assertEquals("Updated Movies are equal", toJson(movieToUpdate), content);
    }

    @Test
    public void getMovieByID() throws Exception {
        Long moviesCount = sequenceService.getLastSequence(Movie.SEQUENCE_NAME);
        long movieIDToGet = new Random().nextInt(moviesCount.intValue() + 1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(MOVIE_CONTROLLER_RESOURCE_PATH + movieIDToGet)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        int httpStatus = mvcResult.getResponse().getStatus();
        assertEquals("HTTP status", 200, httpStatus);

        assertNotNull("Retrieved Object is Movie and Not null", fromJson(mvcResult.getResponse().getContentAsString(), Movie.class));
    }

    @Test
    public void deleteMovieByID() throws Exception {
        Long moviesCount = sequenceService.getLastSequence(Movie.SEQUENCE_NAME);
        int movieIDToDelete = new Random().nextInt(moviesCount.intValue() + 1);

        MvcResult mvcDeleteResult = mockMvc.perform(MockMvcRequestBuilders
                .delete(MOVIE_CONTROLLER_RESOURCE_PATH + movieIDToDelete)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        int httpStatus = mvcDeleteResult.getResponse().getStatus();
        assertEquals("HTTP status", 200, httpStatus);

        String result = mvcDeleteResult.getResponse().getContentAsString();
        assertEquals("Deleted status", "{\"deleted\":true}", result);
    }

    @Test
    public void getMovieByDetails() throws Exception {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> otherResult = new HashMap<>();

        String[] fieldsToGet = {"id","name", "genre", "leadStudio", "audienceScore"};
        String[] otherFieldsToGet = {"id", "profitability", "rottenTomatoesScore", "worldwideGross", "year"};

        Long moviesCount = sequenceService.getLastSequence(Movie.SEQUENCE_NAME);
        int movieIDToGet = new Random().nextInt(moviesCount.intValue() + 1);

        MvcResult mvcMovieObjectResult = mockMvc.perform(MockMvcRequestBuilders
                .get(MOVIE_CONTROLLER_RESOURCE_PATH + movieIDToGet)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        int httpStatus = mvcMovieObjectResult.getResponse().getStatus();
        assertEquals("HTTP status", 200, httpStatus);

        Movie movieOriginal = fromJson(mvcMovieObjectResult.getResponse().getContentAsString(), Movie.class);

        for (String key : fieldsToGet) {
            result.put(key, new PropertyDescriptor(key, Movie.class).getReadMethod().invoke(movieOriginal));
        }

        for (String key : otherFieldsToGet) {
            otherResult.put(key, new PropertyDescriptor(key, Movie.class).getReadMethod().invoke(movieOriginal));
        }

        MvcResult mvcGetMovieByDetailsResult = mockMvc.perform(MockMvcRequestBuilders
                .get(MOVIE_CONTROLLER_RESOURCE_PATH + movieIDToGet + "/" + toJson(fieldsToGet).replaceAll("[\\[\"\\]]",""))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        httpStatus = mvcGetMovieByDetailsResult.getResponse().getStatus();
        assertEquals("HTTP status", 200, httpStatus);

        assertEquals("", fromJson(toJson(result), Map.class), fromJson(mvcGetMovieByDetailsResult.getResponse().getContentAsString(), Map.class));

        MvcResult mvcGetMovieByDetailsOtherResult = mockMvc.perform(MockMvcRequestBuilders
                .get(MOVIE_CONTROLLER_RESOURCE_PATH + movieIDToGet + "/" + toJson(otherFieldsToGet).replaceAll("[\\[\"\\]]",""))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        httpStatus = mvcGetMovieByDetailsOtherResult.getResponse().getStatus();
        assertEquals("HTTP status", 200, httpStatus);

        assertEquals("", fromJson(toJson(otherResult), Map.class), fromJson(mvcGetMovieByDetailsOtherResult.getResponse().getContentAsString(), Map.class));
    }
}