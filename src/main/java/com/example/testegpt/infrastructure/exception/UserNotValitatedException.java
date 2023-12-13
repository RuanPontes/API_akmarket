package com.example.testegpt.infrastructure.exception;


public class UserNotValitatedException extends RuntimeException {

  public UserNotValitatedException(String message) {
    super(message);
  }
}
