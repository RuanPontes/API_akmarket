package com.example.testegpt.service;

import com.example.testegpt.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

  Item save(final Item item);

  void delete(final Long id);

  Item findById(final Long id);

  Page<Item> findItens(Pageable pageable);

}
