package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.exception.CapitalLetterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

  Logger logger = LoggerFactory.getLogger(ErrorController.class);

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    FieldError fieldError = ex.getBindingResult().getFieldError();
    String message = "Object " +
        fieldError.getObjectName() +
        ", field " +
        fieldError.getField() +
        " - " +
        fieldError.getDefaultMessage();
    logger.warn("Handling MethodArgumentNotValidException: " + message);
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid input data", message);
  }

  @ExceptionHandler(CapitalLetterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleCapitalLetterException(CapitalLetterException ex) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid input data", ex.getMessage());
  }

}
