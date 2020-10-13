package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.entity.Director;

import java.util.List;

public interface IDirectorService {

  List<Director> getDirectors();

  Director getDirectorById(int id);

  Director save(Director director);

  Director update(Director director);

  void remove(int id);

  Director getDirectorByName(String name);
}
