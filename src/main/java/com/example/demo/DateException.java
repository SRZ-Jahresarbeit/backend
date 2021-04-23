package com.example.demo;

import java.time.temporal.Temporal;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DateException extends ResponseStatusException {

  public DateException(final Temporal target1, final Temporal target2, final String message) {
    super(HttpStatus.BAD_REQUEST, "%s %s %s".formatted(target1, message, target2));
  }
}
