package com.example.testegpt.repository;

import com.example.testegpt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  User findByUsuario(String usuario);

}
