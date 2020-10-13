package com.oktenweb.javaadvanced.dto;

import com.oktenweb.javaadvanced.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MoviePageDTO {

  private List<Movie> movies;
  private long totalElements;
  private int size;
  private boolean empty;

}
