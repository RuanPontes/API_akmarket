package com.example.testegpt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.testegpt.domain.User;
import com.example.testegpt.repository.UserRepository;
import com.example.testegpt.service.impl.AutenticacaoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutenticacaoServiceImplTest {

  private final UserRepository userRepository = mock(UserRepository.class);
  private AutenticacaoService autenticacaoService;

  @BeforeEach
  void setup() {
    autenticacaoService = new AutenticacaoServiceImpl(userRepository);
  }

  @Test
  void deveCarregarPeloUsername() {
    when(userRepository.findByUsuario(anyString())).thenReturn(getUser());
    User user = (User) autenticacaoService.loadUserByUsername("user_test");

    assertEquals(1L, user.getId());
    assertEquals( "email@email.com", user.getEmail());
    assertEquals( "user_test", user.getUsuario());
  }

  private User getUser() {
    return User.builder()
        .id(1L)
        .email("email@email.com")
        .usuario("user_test")
        .build();
  }

}

