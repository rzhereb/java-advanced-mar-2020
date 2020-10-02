package com.oktenweb.javaadvanced.validator;

import com.oktenweb.javaadvanced.entity.Movie;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;

@Component
public class MovieValidator implements Validator {

  @Override
  public boolean supports(Class<?> aClass) {
    return Movie.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(@Valid Object o, Errors errors) {
    Movie movie = (Movie) o;
    if (movie.getTitle() != null && movie.getTitle().trim().length() > 0) {
      if (movie.getTitle().charAt(0) < 65 || movie.getTitle().charAt(0) > 90) {
        errors.rejectValue("title", "movie.title.capital-letter",
            "Title must start with capital letter");
      }
    }


  }
}
