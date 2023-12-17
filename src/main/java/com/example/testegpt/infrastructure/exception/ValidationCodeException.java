package com.example.testegpt.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidationCodeException extends RuntimeException {

  public ValidationCodeException(String message) {
    super(message);
  }

}
