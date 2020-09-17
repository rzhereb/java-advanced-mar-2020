package com.oktenweb.javaadvanced.dao;

import com.oktenweb.javaadvanced.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movie, Integer> {

}
