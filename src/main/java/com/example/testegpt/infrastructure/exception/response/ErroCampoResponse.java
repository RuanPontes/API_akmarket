package com.example.testegpt.infrastructure.exception.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErroCampoResponse extends ErroResponse {

  private List<ErroCampo> erros;

  public ErroCampoResponse(String mensagem, List<ErroCampo> erros) {
    super(mensagem);
    this.erros = erros;
  }

}
