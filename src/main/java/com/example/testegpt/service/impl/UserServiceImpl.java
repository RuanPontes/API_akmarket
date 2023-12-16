package com.example.testegpt.service.impl;

import com.example.testegpt.domain.Role;
import com.example.testegpt.domain.User;
import com.example.testegpt.domain.enums.Roles;
import com.example.testegpt.infrastructure.exception.EntityAlreadyExistsException;
import com.example.testegpt.infrastructure.exception.EntityNotFoundException;
import com.example.testegpt.repository.UserRepository;
import com.example.testegpt.service.RoleService;
import com.example.testegpt.service.UserService;
import java.util.Collections;
import java.util.Objects;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final RoleService roleService;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, RoleService roleService,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleService = roleService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User findByUsuario(String usuario) {
    return userRepository.findByUsuario(usuario);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public User save(User user) {
    if (userAlreadyExists(user)) {
      throw new EntityAlreadyExistsException(
          "Já existe um usuário cadastrado com o email ou username");
    }

    Role role = roleService.findRoleByName(Roles.ROLE_USER);
    user.setSenha(passwordEncoder.encode(user.getPassword()));
    user.setRoles(Collections.singletonList(role));

    return userRepository.save(user);
  }

  @Override
  public User update(User user) {
    return userRepository.save(user);
  }

  @Override
  public User findById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
  }

  public boolean userAlreadyExists(User user) {
    return Objects.nonNull(findByEmail(user.getEmail()))
        || Objects.nonNull(findByUsuario(user.getUsuario()));
  }
}
