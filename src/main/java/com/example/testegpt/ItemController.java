package com.example.testegpt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemRepository repository;

    ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getItens() {
        List<Item> itens = repository.findAll();
        return ResponseEntity.ok(itens);
    }

    @PostMapping
    public Item newItem(@RequestBody Item newItem) {
        return repository.save(newItem);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
