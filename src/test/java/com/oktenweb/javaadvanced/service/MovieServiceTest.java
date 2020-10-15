package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.dao.DirectorDao;
import com.oktenweb.javaadvanced.dao.MovieDao;
import com.oktenweb.javaadvanced.dto.MovieDTO;
import com.oktenweb.javaadvanced.entity.Director;
import com.oktenweb.javaadvanced.entity.Movie;
import com.oktenweb.javaadvanced.exception.CapitalLetterException;
import com.oktenweb.javaadvanced.service.impl.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

  @Mock
  private MovieDao movieDao;

  @Mock
  private DirectorDao directorDao;

  @InjectMocks
  private MovieService movieService;

  @Test
  public void givenMovieTitleNotStartedWithCapitalWhenInsertingMovieThenPropagateException() {
    Movie movie = new Movie(1, "some", 12, null);

    Assertions.assertThrows(CapitalLetterException.class, () -> movieService.insertMovie(movie, 1));
  }

  @Test
  public void givenValidMovieWhenInsertMovieThenReturnMovieDTO() {

    Director director = new Director(1, "Name", LocalDate.of(1993, 10, 15), Collections.emptyList());
    Movie movie = new Movie(1, "Title", 124, director);

    Mockito.when(directorDao.getOne(ArgumentMatchers.anyInt())).thenReturn(director);
    Mockito.when(movieDao.save(ArgumentMatchers.any(Movie.class))).thenReturn(movie);

    MovieDTO movieDTO = new MovieDTO(1, "Title", 124, "Name");
    final MovieDTO actualMovieDTO = movieService.insertMovie(movie, 1);

    Assertions.assertEquals(movieDTO.getDirectorName(), actualMovieDTO.getDirectorName());
  }



}
