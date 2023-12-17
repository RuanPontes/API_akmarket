package com.example.testegpt.controller.request;

import com.example.testegpt.domain.User;

public record UserResponse(
    String usuario,
    String email
) {
  public UserResponse(User user) {
    this(user.getUsuario(), user.getEmail());
  }
}
