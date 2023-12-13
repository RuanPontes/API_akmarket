package com.example.testegpt.controller;

import com.example.testegpt.controller.request.UserRequest;
import com.example.testegpt.controller.request.UserResponse;
import com.example.testegpt.domain.User;
import com.example.testegpt.service.UserService;
import com.example.testegpt.service.ValidationCodeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signup")
public class CadastroUsuarioController {

  private final UserService userService;
  private final ValidationCodeService validationCodeService;

  public CadastroUsuarioController(UserService userService, ValidationCodeService validationCodeService) {
    this.userService = userService;
    this.validationCodeService = validationCodeService;
  }

  @PostMapping
  public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest userRequest) {
    User user = userRequest.toUser();
    user = userService.save(user);
    validationCodeService.create(user.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(user));
  }

}

