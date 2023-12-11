package com.example.testegpt.infrastructure;

public class TokenException extends RuntimeException {

  public TokenException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
