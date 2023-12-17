package com.example.testegpt.service.impl;

import com.example.testegpt.domain.Role;
import com.example.testegpt.domain.enums.Roles;
import com.example.testegpt.repository.RoleRepository;
import com.example.testegpt.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  public RoleServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public Role findRoleByName(Roles role) {
    return roleRepository.findByNome(role);
  }
}
