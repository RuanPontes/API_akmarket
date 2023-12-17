package com.example.testegpt.infrastructure.exception;


public class EntityAlreadyExistsException extends RuntimeException {
  
  public EntityAlreadyExistsException(String message) {
    super(message);
  }
  
}
