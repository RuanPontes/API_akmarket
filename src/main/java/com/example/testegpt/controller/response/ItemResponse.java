package com.example.testegpt.controller.response;

import com.example.testegpt.domain.Item;

public record ItemResponse(
    Long id,
    String nome,
    Double valor,
    String tipo,
    String adds,
    String classe
    ) {
    public ItemResponse(Item item) {
      this(item.getId(), item.getNome(), item.getValor(), item.getTipo(), item.getAdds(), item.getClasse());
    }
}
