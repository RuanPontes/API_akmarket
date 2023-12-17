package com.example.testegpt.service;

import com.example.testegpt.domain.Role;
import com.example.testegpt.domain.enums.Roles;

public interface RoleService {

  Role findRoleByName(Roles role);

}
