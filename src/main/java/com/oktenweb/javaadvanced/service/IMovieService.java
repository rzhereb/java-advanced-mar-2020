package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.dto.MovieDTO;
import com.oktenweb.javaadvanced.dto.MoviePageDTO;
import com.oktenweb.javaadvanced.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IMovieService {

  MovieDTO insertMovie(Movie movie, int directorId);

  MoviePageDTO getAllMovies(PageRequest pageRequest);

  Movie getMovie(int id);

  Movie updateMovie(Movie movie);

  void removeMovie(int id);


  List<Movie> getMoviesByDirectorName(String name);
}
