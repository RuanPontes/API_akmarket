package com.example.testegpt.infrastructure.exception.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErroResponse {

  private String mensagem;
  private String timestamp;

  public ErroResponse(Exception exception) {
    this.mensagem = exception.getMessage();
    this.timestamp = LocalDateTime.now().toString();
  }

  public ErroResponse(String mensagem) {
    this.mensagem = mensagem;
    this.timestamp = LocalDateTime.now().toString();
  }

}
