package com.oktenweb.javaadvanced.validator;

import com.oktenweb.javaadvanced.dao.MovieDao;
import com.oktenweb.javaadvanced.entity.Movie;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class UniqueMovieTitleValidator implements ConstraintValidator<UniqueMovieTitle, String> {

  // not working with beans! If you need validation using DAO, use Service layer validation instead
  private MovieDao movieDao;

  @Autowired
  public UniqueMovieTitleValidator(MovieDao movieDao) {
    this.movieDao = movieDao;
  }

  @Override
  public void initialize(UniqueMovieTitle constraintAnnotation) {

  }

  @Override
  public boolean isValid(String title, ConstraintValidatorContext context) {
    if (title != null && title.length() != 0) {
      final Movie movie = movieDao.findByTitle(title);
      return movie == null;
    }
    return false;
  }
}
