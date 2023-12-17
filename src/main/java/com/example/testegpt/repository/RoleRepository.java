package com.example.testegpt.repository;

import com.example.testegpt.domain.Role;
import com.example.testegpt.domain.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByNome(Roles name);

}
