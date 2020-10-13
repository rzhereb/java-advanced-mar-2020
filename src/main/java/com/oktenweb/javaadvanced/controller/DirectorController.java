package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.entity.Director;
import com.oktenweb.javaadvanced.service.IDirectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/directors")
public class DirectorController {

  private IDirectorService directorService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Director saveDirector(@RequestBody Director director) {
    return directorService.save(director);
  }

  @GetMapping
  public List<Director> getAllDirectors() {
    return directorService.getDirectors();
  }

  @GetMapping("/{name}")
  public Director getDirectorByName(@PathVariable String name) {
    return directorService.getDirectorByName(name);
  }
}
