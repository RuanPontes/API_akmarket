package com.example.testegpt.infrastructure.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErroResponse {

  private String mensagem;
  private String timestamp;

}
