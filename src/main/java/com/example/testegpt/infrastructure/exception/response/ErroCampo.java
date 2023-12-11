package com.example.testegpt.infrastructure.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErroCampo {

  private String campo;
  private String mensagem;

  public ErroCampo(FieldError fieldError) {
    this(fieldError.getField(), fieldError.getDefaultMessage());
  }

}
