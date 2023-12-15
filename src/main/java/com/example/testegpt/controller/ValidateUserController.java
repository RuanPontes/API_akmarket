package com.example.testegpt.controller;

import com.example.testegpt.controller.request.UserValidationRequest;
import com.example.testegpt.controller.response.UserValidationResponse;
import com.example.testegpt.service.ValidationCodeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ValidateUserController {

  private final ValidationCodeService validationCodeService;

  public ValidateUserController(ValidationCodeService validationCodeService) {
    this.validationCodeService = validationCodeService;
  }

  @PostMapping("/{id}/validate")
  public ResponseEntity<UserValidationResponse> validate(
      @PathVariable Long id, @RequestBody @Valid UserValidationRequest request) {
    validationCodeService.validate(id, Integer.parseInt(request.codigo()));

    return ResponseEntity.ok(new UserValidationResponse("Código validado com sucesso!"));
  }

  @PostMapping("/{id}/resend")
  public ResponseEntity<UserValidationResponse> resend(@PathVariable Long id) {
    validationCodeService.resend(id);
    return ResponseEntity.ok(new UserValidationResponse("O código foi reenviado"));
  }

}
