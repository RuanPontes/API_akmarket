package com.example.testegpt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.testegpt.domain.Item;
import com.example.testegpt.repository.ItemRepository;
import com.example.testegpt.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemServiceImplTest {

  private ItemService itemService;
  private final ItemRepository itemRepository = mock(ItemRepository.class);
  private final UserService userService = mock(UserService.class);
  private final ValidationCodeService validationCodeService = mock(ValidationCodeService.class);
  private final TokenService tokenService = mock(TokenService.class);

  @BeforeEach
  void setup() {
    itemService = new ItemServiceImpl(itemRepository, userService, validationCodeService, tokenService);
  }

  @Test
  void deveSalvarItem() {
    when(itemRepository.save(any(Item.class))).thenReturn(getItem());
    Item saved = itemService.save(getItem());

    assertEquals(1L, saved.getId());
    assertEquals(50.0, saved.getValor());
  }

  @Test
  void deveSalvarItemPeloToken() {

  }

  private Item getItem() {
    return Item.builder()
        .id(1L)
        .valor(50.0)
        .idUsuario(1L)
        .build();
  }

}
