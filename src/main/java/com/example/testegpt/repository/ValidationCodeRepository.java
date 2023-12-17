package com.example.testegpt.repository;

import com.example.testegpt.domain.ValidationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationCodeRepository extends JpaRepository<ValidationCode, Long> {

  ValidationCode findByUserId(Long userId);

}
