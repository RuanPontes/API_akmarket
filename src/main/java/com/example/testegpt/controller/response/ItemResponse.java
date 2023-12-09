package com.example.testegpt.controller.response;

import com.example.testegpt.domain.Item;

public record ItemResponse(
    Long id,
    Double valor,
    String tipo,
    String adds,
    String classe
    ) {
    public ItemResponse(Item item) {
      this(item.getId(), item.getValor(), item.getTipo(), item.getAdds(), item.getClasse());
    }
}
