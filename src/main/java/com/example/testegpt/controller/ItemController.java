package com.example.testegpt.controller;

import com.example.testegpt.controller.request.ItemRequest;
import com.example.testegpt.controller.response.ItemResponse;
import com.example.testegpt.domain.Item;
import com.example.testegpt.service.ItemService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public ResponseEntity<Page<ItemResponse>> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size) {

        List<ItemResponse> itens = itemService
            .findItens(PageRequest.of(page, size))
            .stream()
            .map(ItemResponse::new)
            .toList();

        return ResponseEntity.ok(new PageImpl<>(itens));
    }

    @PostMapping
    public ResponseEntity<ItemResponse> create(@RequestBody @Valid ItemRequest request) {
        Item item = itemService.save(request.toItem());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ItemResponse(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
