package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.dto.MovieDTO;
import com.oktenweb.javaadvanced.dto.MoviePageDTO;
import com.oktenweb.javaadvanced.entity.Movie;
import com.oktenweb.javaadvanced.service.IMovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {

  @Qualifier(value = "movieService")
  private IMovieService movieService;
//    private MovieValidator movieValidator;

  @Autowired
  public MovieController(IMovieService movieService) {
    this.movieService = movieService;
//        this.movieValidator = movieValidator;
  }

  //@RequestMapping(value = "/movies", method = RequestMethod.GET)
  @GetMapping
  public MoviePageDTO getMovies(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "3") int size) {
      final PageRequest pageRequest = PageRequest.of(page, size);
      return movieService.getAllMovies(pageRequest);
  }

  @GetMapping(value = "/{id}")
  public Movie getMovie(@PathVariable int id) {
    final Movie movie = movieService.getMovie(id);
    return movie;
  }

  @GetMapping("/name/{name}")
  public List<Movie> getMovies(@PathVariable String name) {
    return movieService.getMoviesByDirectorName(name);
  }

  @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> getMovieImage(@PathVariable int id) {
    return ResponseEntity.ok().body(movieService.getMovieImage(id));
  }

  // Code for RSS:
//
//  String feedUrl = "https://stackoverflow.com/feeds";
//  RestTemplate restTemplate = new RestTemplate();
//  SyndFeed syndFeed = restTemplate.execute(feedUrl, HttpMethod.GET, null, response -> {
//    SyndFeedInput input = new SyndFeedInput();
//    try {
//      return input.build(new XmlReader(response.getBody()));
//    } catch (FeedException e) {
//      throw new IOException("Could not parse response", e);
//    }
//  });

  @PostMapping(value = "/directors/{directorId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public MovieDTO insertMovie(@ModelAttribute @Valid Movie movie, @PathVariable int directorId,
      @RequestParam(required = false) MultipartFile file) {
    log.info("Handling POST /movie with object: " + movie);
    return movieService.insertMovie(movie, file, directorId);
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie) {
    movie.setId(id);
    return movieService.updateMovie(movie);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteMovie(@PathVariable int id) {
    movieService.removeMovie(id);
  }

//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder) {
////        webDataBinder.addValidators(new MovieValidator());
//        webDataBinder.addValidators(movieValidator);
//    }
}
