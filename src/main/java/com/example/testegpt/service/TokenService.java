package com.example.testegpt.service;

import com.example.testegpt.domain.User;

public interface TokenService {

  String gerarToken(User user);

  String getSubject(String jwtToken);

}
