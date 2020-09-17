package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.dao.MovieDao;
import com.oktenweb.javaadvanced.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieDao movieDao;

    @Autowired
    public MovieController(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    //@RequestMapping(value = "/movies", method = RequestMethod.GET)
    @GetMapping
    public List<Movie> getMovies() {
        return movieDao.findAll();
    }

    @GetMapping(value = "/{id}")
    public Movie getMovie(@PathVariable int id) {
        return movieDao.findById(id).orElseThrow(() -> new RuntimeException("No movie with id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie insertMovie(@RequestBody Movie movie) {
        movieDao.save(movie);
        return movie;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        movie.setId(id);
        movieDao.save(movie);
        return movie;

//        //todo: getOne explore
//        Movie one = movieDao.getOne(id);
//        one.setDuration(movie.getDuration());
//        one.setTitle(movie.getTitle());
//        movieDao.flush();
//        return movie;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable int id) {
        movieDao.deleteById(id);
    }
}
