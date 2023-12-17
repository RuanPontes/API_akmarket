package com.example.testegpt.controller;

import com.example.testegpt.controller.request.LoginRequest;
import com.example.testegpt.controller.response.LoginResponse;
import com.example.testegpt.domain.User;
import com.example.testegpt.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AutenticacaoController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  public AutenticacaoController(
      AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    var token = new UsernamePasswordAuthenticationToken(request.usuario(), request.senha());
    Authentication authentication = authenticationManager.authenticate(token);

    return ResponseEntity.ok(
        new LoginResponse(tokenService.gerarToken((User) authentication.getPrincipal())));
  }
}
