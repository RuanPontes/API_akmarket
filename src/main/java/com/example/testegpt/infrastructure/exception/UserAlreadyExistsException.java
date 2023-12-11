package com.example.testegpt.infrastructure.exception;


public class UserAlreadyExistsException extends RuntimeException {
  
  public UserAlreadyExistsException(String message) {
    super(message);
  }
  
}
