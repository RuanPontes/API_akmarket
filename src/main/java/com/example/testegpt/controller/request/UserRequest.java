package com.example.testegpt.controller.request;

import com.example.testegpt.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank(message = "não pode estar vazio") String usuario,
    @Size(message = "mínimo 8 caracteres", min = 8, max = 100) String senha,
    @NotBlank(message = "não pode estar vazio")
    @Email String email,
    String telefone
) {
  public User toUser() {
    return User.builder()
        .usuario(usuario)
        .senha(senha)
        .email(email)
        .telefone(telefone)
        .habilitado(true)
        .build();
  }
}
