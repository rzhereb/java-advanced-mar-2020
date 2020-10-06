package com.oktenweb.javaadvanced.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

  private int status;
  private String title;
  private String message;

}
