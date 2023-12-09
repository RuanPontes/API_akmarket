package com.example.testegpt.controller.request;

import com.example.testegpt.domain.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRequest(
    @NotBlank(message = "não pode ser vazio") String nome,
    @NotNull(message = "não pode ser vazio") Double valor,
    @NotBlank(message = "não pode ser vazio") String tipo,
    @NotBlank(message = "não pode ser vazio")  String adds,
    @NotBlank(message = "não pode ser vazio") String classe) {

  public Item toItem() {
    return Item.builder()
        .nome(nome)
        .valor(valor)
        .tipo(tipo)
        .classe(classe)
        .build();
  }

}
