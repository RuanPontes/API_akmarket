package com.example.testegpt.controller;

import static com.example.testegpt.constants.Headers.AUTHORIZATION;

import com.example.testegpt.controller.request.UserValidationRequest;
import com.example.testegpt.controller.response.UserValidationResponse;
import com.example.testegpt.service.TokenService;
import com.example.testegpt.service.UserService;
import com.example.testegpt.service.ValidationCodeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ValidateUserController {

  private final ValidationCodeService validationCodeService;
  private final UserService userService;
  private final TokenService tokenService;

  public ValidateUserController(
      ValidationCodeService validationCodeService,
      UserService userService,
      TokenService tokenService) {
    this.validationCodeService = validationCodeService;
    this.userService = userService;
    this.tokenService = tokenService;
  }

  @PostMapping("/validate")
  public ResponseEntity<UserValidationResponse> validate(
      @RequestBody @Valid UserValidationRequest request, HttpServletRequest servletRequest) {
    validationCodeService.validate(getUserId(servletRequest), Integer.parseInt(request.codigo()));

    return ResponseEntity.ok(new UserValidationResponse("Código validado com sucesso!"));
  }

  @PostMapping("/resend")
  public ResponseEntity<UserValidationResponse> resend(HttpServletRequest request) {
    validationCodeService.resend(getUserId(request));
    return ResponseEntity.ok(new UserValidationResponse("O código foi reenviado"));
  }

  private Long getUserId(HttpServletRequest request) {
    String subject = tokenService.getSubject(request.getHeader(AUTHORIZATION));
    return userService.findByUsuario(subject).getId();
  }
}
