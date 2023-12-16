package com.example.testegpt.repository;

import com.example.testegpt.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

  Page<Item> findItemsByIdUsuario(Pageable pageable, Long userId);

}
