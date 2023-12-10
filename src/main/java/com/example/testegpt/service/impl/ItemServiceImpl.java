package com.example.testegpt.service.impl;

import com.example.testegpt.domain.Item;
import com.example.testegpt.repository.ItemRepository;
import com.example.testegpt.service.ItemService;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;

  public ItemServiceImpl(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public Item save(final Item item) {
    return itemRepository.save(item);
  }

  @Override
  public void delete(Long id) {
    Item item = findById(id);

    if (Objects.isNull(item)) {
      throw new RuntimeException("Item not found!");
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
