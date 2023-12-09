package com.example.testegpt.controller;

import com.example.testegpt.domain.Item;
import com.example.testegpt.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemService itemService;
    ItemController(final ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<Page<Item>> getItens(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size) {
        Page<Item> itens = itemService.findItens(PageRequest.of(page, size));
        return ResponseEntity.ok(itens);
    }

    @PostMapping
    public ResponseEntity<Item> newItem(@RequestBody Item newItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(newItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
