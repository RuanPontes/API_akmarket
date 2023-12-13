package com.example.testegpt.service.impl;

import com.example.testegpt.domain.Item;
import com.example.testegpt.domain.User;
import com.example.testegpt.infrastructure.exception.EntityNotFoundException;
import com.example.testegpt.repository.ItemRepository;
import com.example.testegpt.service.ItemService;
import com.example.testegpt.service.UserService;
import com.example.testegpt.service.ValidationCodeService;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;
  private final UserService userService;
  private final ValidationCodeService validationCodeService;

  public ItemServiceImpl(
      ItemRepository itemRepository, UserService userService, ValidationCodeService validationCodeService) {
    this.itemRepository = itemRepository;
    this.userService = userService;
    this.validationCodeService = validationCodeService;
  }

  public Item save(final Item item) {
    return itemRepository.save(item);
  }

  public Item save(final String token, final Item item) {
    User user = userService.findByToken(token);
    validationCodeService.verify(token);

    item.setIdUsuario(user.getId());
    item.setDataCriacao(LocalDateTime.now());
    item.setDataAtualizacao(LocalDateTime.now());

    return save(item);
  }

  @Override
  public void delete(Long id) {
    Item item = findById(id);

    if (Objects.isNull(item)) {
      throw new EntityNotFoundException("Item %s não encontrado".formatted(id));
    }

    itemRepository.delete(item);
  }

  @Override
  public Item findById(Long id) {
    return itemRepository.findById(id).orElse(null);
  }

  @Override
  public Page<Item> findItens(Pageable pageable) {
    return itemRepository.findAll(pageable);
  }
}
