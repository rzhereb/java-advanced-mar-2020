package com.oktenweb.javaadvanced.service.impl;

import com.oktenweb.javaadvanced.config.StorageProperties;
import com.oktenweb.javaadvanced.dao.DirectorDao;
import com.oktenweb.javaadvanced.dao.MovieDao;
import com.oktenweb.javaadvanced.dto.MovieDTO;
import com.oktenweb.javaadvanced.dto.MoviePageDTO;
import com.oktenweb.javaadvanced.entity.Director;
import com.oktenweb.javaadvanced.entity.Movie;
import com.oktenweb.javaadvanced.exception.CapitalLetterException;
import com.oktenweb.javaadvanced.service.IMovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;

@Service
@Slf4j
public class MovieService implements IMovieService {

  @Autowired
  private MovieDao movieDao;

  @Autowired
  private DirectorDao directorDao;

  @Autowired
  private StorageProperties storageProperties;

  private Path rootFolder;

  @PostConstruct
  public void init() {
    try {
      rootFolder = Paths.get(storageProperties.getLocation()).toAbsolutePath().normalize();
      Files.createDirectory(rootFolder);
    } catch (IOException e) {
      log.error("Unable to create folder " + e.getMessage());
    }
  }

  @Override
  public MovieDTO insertMovie(Movie movie, MultipartFile file, int directorId) {
    if (movie.getTitle().charAt(0) < 65 || movie.getTitle().charAt(0) > 90) {
      throw new CapitalLetterException("Title should start with capital letter");
    }
    final Director director = directorDao.getOne(directorId);
    movie.setDirector(director);
    try {
      movie.setData(file.getBytes());
    } catch (IOException e) {
      log.warn("No file " + e.getMessage());
    }
    movie = movieDao.save(movie);

//    int id = movie.getId();
//    try {
//      final String extension = Objects.requireNonNull(file.getOriginalFilename())
//          .substring(file.getOriginalFilename().indexOf("."));
//      Path filePath = Paths.get(id + extension).normalize();
//      Files.copy(file.getInputStream(), rootFolder.resolve(filePath));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    return new MovieDTO(movie.getId(), movie.getTitle(), movie.getDuration(), director.getName());
  }

  @Override
  public MoviePageDTO getAllMovies(PageRequest pageRequest) {
    final Page<Movie> all = movieDao.findAll(pageRequest);

    return new MoviePageDTO(all.getContent(), all.getTotalElements(), all.getSize(), all.isEmpty());
  }

  @Override
  public Movie getMovie(int id) {
    return movieDao.findById(id).orElseThrow(() -> new RuntimeException("No movie with id: " + id));
//        return movieDao.getOne(id);
  }

  @Override
  public Movie updateMovie(Movie movie) {
    return movieDao.save(movie);
  }

  @Override
  public void removeMovie(int id) {
    movieDao.deleteById(id);
  }

  @Override
  public List<Movie> getMoviesByDirectorName(String name) {
    return movieDao.findByDirectorName(name);
  }

  @Override
  public byte[] getMovieImage(int id) {
    return movieDao.findById(id).orElseThrow(() -> new RuntimeException("No movie with id: " + id)).getData();
  }

}
