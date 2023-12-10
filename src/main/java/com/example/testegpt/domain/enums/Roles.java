package com.example.testegpt.domain.enums;

import java.util.Arrays;

public enum Roles {
  USER,
  ADMIN;

  public static Roles getRole(String role) {
    return Arrays.stream(Roles.values())
        .filter(r -> role.equalsIgnoreCase(r.name()))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Role not found!"));
  }
}
