package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.entity.Movie;

import java.util.List;

public interface IMovieService {

  Movie insertMovie(Movie movie);

  List<Movie> getAllMovies();

  Movie getMovie(int id);

  Movie updateMovie(Movie movie);

  void removeMovie(int id);

}
