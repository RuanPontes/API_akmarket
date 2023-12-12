package com.example.testegpt.controller;

import com.example.testegpt.controller.request.ItemRequest;
import com.example.testegpt.controller.response.ItemResponse;
import com.example.testegpt.domain.Item;
import com.example.testegpt.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens")
public class ItemController {

  private final ItemService itemService;

  ItemController(final ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping
  public ResponseEntity<Page<ItemResponse>> findAll(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
    List<ItemResponse> itens =
        itemService.findItens(PageRequest.of(page, size)).stream().map(ItemResponse::new)
            .collect(Collectors.toList());

    return ResponseEntity.ok(new PageImpl<>(itens, Pageable.ofSize(size), itens.size()));
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  public ResponseEntity<ItemResponse> create(@RequestBody @Valid ItemRequest request, HttpServletRequest servletRequest) {
    Item item = itemService.save(servletRequest.getHeader("Authorization"), request.toItem());
    return ResponseEntity.status(HttpStatus.CREATED).body(new ItemResponse(item));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    itemService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
