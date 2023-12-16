package com.example.testegpt.service.impl;

import com.example.testegpt.domain.User;
import com.example.testegpt.domain.ValidationCode;
import com.example.testegpt.infrastructure.exception.ValidationCodeException;
import com.example.testegpt.repository.ValidationCodeRepository;
import com.example.testegpt.service.TokenService;
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
  private final TokenService tokenService;

  public ValidationCodeServiceImpl(
      ValidationCodeRepository validationCodeRepository, UserService userService, TokenService tokenService) {
    this.validationCodeRepository = validationCodeRepository;
    this.userService = userService;
    this.tokenService = tokenService;
  }

  @Override
  public ValidationCode save(ValidationCode code) {
    return validationCodeRepository.save(code);
  }

  @Override
  public ValidationCode create(Long userId) {
    ValidationCode code =
        ValidationCode.builder()
            .codigo(generateCode())
            .dataValidade(getExpirationTime())
            .userId(userId)
            .isValidado(false)
            .build();

    return save(code);
  }

  @Override
  public ValidationCode validate(Long userId, Integer codigo) {
    User user = userService.findById(userId);
    ValidationCode code = getValidationCode(user.getId());

    if (!isValid(codigo, code)) {
      throw new ValidationCodeException("Código inválido ou expirado!");
    }

    user.setPodeNegociar(Boolean.TRUE);
    code.setIsValidado(Boolean.TRUE);
    code.setDataValidacao(getCurrentLocalDateTime());

    code = save(code);
    userService.update(user);

    return code;
  }

  @Override
  public void verify(String token) {
    User user = userService.findByUsuario(tokenService.getSubject(token));
    ValidationCode code = getValidationCode(user.getId());

    if (!code.getIsValidado()) {
      throw new ValidationCodeException("O código não foi validado, valide para continuar");
    }
  }

  @Override
  public void resend(Long userId) {
    ValidationCode code = validationCodeRepository.findByUserId(userId);

    if (code.getIsValidado()) {
      throw new ValidationCodeException("Usuário já validado!");
    }

    code.setCodigo(generateCode());
    code.setDataValidade(getExpirationTime());
    validationCodeRepository.save(code);
  }

  private ValidationCode getValidationCode(Long id) {
    return validationCodeRepository.findByUserId(id);
  }

  private LocalDateTime getExpirationTime() {
    return LocalDateTime.now().plusMinutes(5).atZone(ZoneId.of("-03:00")).toLocalDateTime();
  }

  private LocalDateTime getCurrentLocalDateTime() {
    return LocalDateTime.now(ZoneId.of("-03:00"));
  }

  private boolean isValid(Integer code, ValidationCode validationCode) {
    return Objects.equals(code, validationCode.getCodigo())
        && getCurrentLocalDateTime().isBefore(validationCode.getDataValidade())
        && !validationCode.getIsValidado();
  }

  private Integer generateCode() {
    return ThreadLocalRandom.current().nextInt(100000, 1000000);
  }
}
