package com.oktenweb.javaadvanced.dao;

import com.oktenweb.javaadvanced.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieDao extends JpaRepository<Movie, Integer> {

  @Query("select m from Movie m where m.title=:title")
  Movie findByTitle(String title);

  @Query("select m from Movie m join m.director d where d.name=:name")
  List<Movie> findByDirectorName(String name);
}
