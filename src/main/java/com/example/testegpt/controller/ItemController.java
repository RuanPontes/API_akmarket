package com.example.testegpt.controller;

import com.example.testegpt.controller.request.ItemRequest;
import com.example.testegpt.controller.response.ItemResponse;
import com.example.testegpt.domain.Item;
import com.example.testegpt.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens")
public class ItemController {

  private final ItemService itemService;

  ItemController(final ItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping
  public ResponseEntity<ItemResponse> create(@RequestBody @Valid ItemRequest request, HttpServletRequest servletRequest) {
    Item item = itemService.save(servletRequest.getHeader("Authorization"), request.toItem());
    return ResponseEntity.status(HttpStatus.CREATED).body(new ItemResponse(item));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    itemService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
