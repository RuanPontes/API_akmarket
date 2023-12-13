package com.example.testegpt.service;

import com.example.testegpt.domain.User;

public interface UserService {

  User findByUsuario(String usuario);

  User findByEmail(String email);

  User save(User user);

  User update(User user);

  User findById(Long id);

}
