package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.entity.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    private List<Movie> movies = new ArrayList<>();

    {
        movies.add(new Movie(1, "LoTR: TFoF", 125));
        movies.add(new Movie(2, "Harry Potter: GoF", 110));
    }

    //    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    @GetMapping(value = "/movies")
    public List<Movie> getMovies() {
        return movies;
    }

    @GetMapping(value = "/movies/{id}")
    public Movie getMovie(@PathVariable int id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such movie"));
    }

    @PostMapping(value = "/movies")
    public Movie insertMovie(@RequestBody Movie movie) {
        movies.add(movie);
        return movie;
    }
}

