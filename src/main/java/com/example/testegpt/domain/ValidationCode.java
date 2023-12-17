package com.example.testegpt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "validation_codes")
public class ValidationCode {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Integer codigo;

  @Column(nullable = false, name = "data_validade")
  private LocalDateTime dataValidade;

  @Column(nullable = false, name = "is_validado")
  private Boolean isValidado;

  @Column(nullable = false, name = "user_id")
  private Long userId;

  @Column(name = "data_validacao")
  private LocalDateTime dataValidacao;
}
