package com.oktenweb.javaadvanced.service.impl;

import com.oktenweb.javaadvanced.dao.DirectorDao;
import com.oktenweb.javaadvanced.entity.Director;
import com.oktenweb.javaadvanced.service.IDirectorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService implements IDirectorService {

  private DirectorDao directorDao;

  public DirectorService(DirectorDao directorDao) {
    this.directorDao = directorDao;
  }

  @Override
  public List<Director> getDirectors() {
    return directorDao.findAll();
  }

  @Override
  public Director getDirectorById(int id) {
    return directorDao.getOne(id);
  }

  @Override
  public Director save(Director director) {
    return directorDao.save(director);
  }

  @Override
  public Director update(Director director) {
    return directorDao.save(director);
  }

  @Override
  public void remove(int id) {
    directorDao.deleteById(id);
  }
}
