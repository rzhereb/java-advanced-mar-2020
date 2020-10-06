package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.entity.Movie;
import com.oktenweb.javaadvanced.service.IMovieService;
import com.oktenweb.javaadvanced.validator.MovieValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {

    @Qualifier(value = "movieService")
    private IMovieService movieService;
//    private MovieValidator movieValidator;

    @Autowired
    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
//        this.movieValidator = movieValidator;
    }

    //@RequestMapping(value = "/movies", method = RequestMethod.GET)
    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(value = "/{id}")
    public Movie getMovie(@PathVariable int id) {
        return movieService.getMovie(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie insertMovie(@RequestBody @Valid Movie movie) {
        log.info("Handling POST /movie with object: " + movie);
        return movieService.insertMovie(movie);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        movie.setId(id);
        return movieService.updateMovie(movie);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable int id) {
        movieService.removeMovie(id);
    }

//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder) {
////        webDataBinder.addValidators(new MovieValidator());
//        webDataBinder.addValidators(movieValidator);
//    }
}
