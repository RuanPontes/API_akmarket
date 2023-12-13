package com.example.testegpt.service.impl;

import com.example.testegpt.domain.User;
import com.example.testegpt.domain.ValidationCode;
import com.example.testegpt.repository.ValidationCodeRepository;
import com.example.testegpt.service.UserService;
import com.example.testegpt.service.ValidationCodeService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;

@Service
public class ValidationCodeServiceImpl implements ValidationCodeService {

  private final ValidationCodeRepository validationCodeRepository;
  private final UserService userService;

  public ValidationCodeServiceImpl(
      ValidationCodeRepository validationCodeRepository,
      UserService userService) {
    this.validationCodeRepository = validationCodeRepository;
    this.userService = userService;
  }

  @Override
  public ValidationCode save(ValidationCode code) {
    return validationCodeRepository.save(code);
  }

  @Override
  public ValidationCode create(Long userId) {
    ValidationCode code =
        ValidationCode.builder()
            .codigo(ThreadLocalRandom.current().nextInt(100000, 1000000))
            .dataValidade(getExpirationTime())
            .userId(userId)
            .isValidado(false)
            .build();

    return save(code);
  }

  @Override
  public ValidationCode validate(Long userId, Integer codigo) {
    User user = userService.findById(userId);
    ValidationCode code = validationCodeRepository.findByUserId(user.getId());

    if (!isValid(codigo, code)) {
      throw new RuntimeException("Código inválido ou expirado!");
    }

    user.setPodeNegociar(Boolean.TRUE);
    code.setIsValidado(Boolean.TRUE);
    code.setDataValidacao(getCurrentLocalDateTime());

    code = save(code);
    userService.update(user);

    return code;
  }

  private LocalDateTime getExpirationTime() {
    return LocalDateTime.now()
        .plusMinutes(5)
        .atZone(ZoneId.of("-03:00"))
        .toLocalDateTime();
  }

  private LocalDateTime getCurrentLocalDateTime() {
    return LocalDateTime.now(ZoneId.of("-03:00"));
  }

  private boolean isValid(Integer code, ValidationCode validationCode) {
    return Objects.equals(code, validationCode.getCodigo())
        && getCurrentLocalDateTime().isBefore(validationCode.getDataValidade());
  }
}
