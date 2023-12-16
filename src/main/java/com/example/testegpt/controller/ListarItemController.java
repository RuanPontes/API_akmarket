package com.example.testegpt.controller;

import com.example.testegpt.controller.response.ItemResponse;
import com.example.testegpt.service.ItemService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListarItemController {

  private final ItemService itemService;

  public ListarItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("/itens")
  public ResponseEntity<Page<ItemResponse>> findAll(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
    List<ItemResponse> itens =
        itemService.findItens(PageRequest.of(page, size)).stream()
            .map(ItemResponse::new)
            .toList();

    return ResponseEntity.ok(new PageImpl<>(itens, Pageable.ofSize(size), itens.size()));
  }

  @GetMapping("/users/{id}/itens")
  public ResponseEntity<Page<ItemResponse>> findByUserId(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size, @PathVariable Long id) {
    List<ItemResponse> itens = 
        itemService.findItensByUserId(PageRequest.of(page, size), id).stream()
            .map(ItemResponse::new)
            .toList();
    
    return ResponseEntity.ok(new PageImpl<>(itens, Pageable.ofSize(size), itens.size()));
  }
}
