package com.oktenweb.javaadvanced.service.impl;

import com.oktenweb.javaadvanced.dao.DirectorDao;
import com.oktenweb.javaadvanced.dao.MovieDao;
import com.oktenweb.javaadvanced.dto.MovieDTO;
import com.oktenweb.javaadvanced.entity.Director;
import com.oktenweb.javaadvanced.entity.Movie;
import com.oktenweb.javaadvanced.exception.CapitalLetterException;
import com.oktenweb.javaadvanced.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements IMovieService {

  @Autowired
  private MovieDao movieDao;

  @Autowired
  private DirectorDao directorDao;

  @Override
  public MovieDTO insertMovie(Movie movie, int directorId) {
    if (movie.getTitle().charAt(0) < 65 || movie.getTitle().charAt(0) > 90) {
      throw new CapitalLetterException("Title should start with capital letter");
    }
    final Director director = directorDao.getOne(directorId);
    movie.setDirector(director);
    movie = movieDao.save(movie);
    return new MovieDTO(movie.getId(), movie.getTitle(), movie.getDuration(), director.getName());
  }

  @Override
  public List<Movie> getAllMovies() {
    return movieDao.findAll();
  }

  @Override
  public Movie getMovie(int id) {
    return movieDao.findById(id).orElseThrow(() -> new RuntimeException("No movie with id: " + id));
//        return movieDao.getOne(id);
  }

  @Override
  public Movie updateMovie(Movie movie) {
    return movieDao.save(movie);
  }

  @Override
  public void removeMovie(int id) {
    movieDao.deleteById(id);
  }
}
