package com.example.testegpt.service;

import com.example.testegpt.domain.ValidationCode;

public interface ValidationCodeService {

  ValidationCode save(ValidationCode code);

  ValidationCode create(Long userId);

  ValidationCode validate(Long userId, Integer codigo);

  void verify(String token);

}
