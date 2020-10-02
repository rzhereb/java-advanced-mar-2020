package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.dao.MovieDao;
import com.oktenweb.javaadvanced.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements IMovieService {

  @Autowired
  private MovieDao movieDao;

  @Override
  public Movie insertMovie(Movie movie) {
    if (movie.getTitle().charAt(0) < 65 || movie.getTitle().charAt(0) > 90) {
      //todo: rewrite in Exception Handling lesson
      throw new RuntimeException("Title should start with capital letter");
    }
    return movieDao.save(movie);
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
