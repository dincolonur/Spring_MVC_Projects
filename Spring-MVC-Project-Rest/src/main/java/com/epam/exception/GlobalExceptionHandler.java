package com.epam.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ModelAndView handleAllException(Exception ex) {

    ModelAndView model = new ModelAndView("exceptionPage");
    model.addObject("message", ex.getCause().getMessage());

    return model;
  }
}
