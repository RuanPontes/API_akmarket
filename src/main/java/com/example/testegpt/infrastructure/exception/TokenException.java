package com.example.testegpt.infrastructure.exception;

public class TokenException extends RuntimeException {

  public TokenException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
