package com.example.testegpt.service.impl;

import com.example.testegpt.repository.UserRepository;
import com.example.testegpt.service.AutenticacaoService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

  private final UserRepository userRepository;

  public AutenticacaoServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsuario(username);
  }
}
