package com.example.testegpt.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.testegpt.domain.User;
import com.example.testegpt.service.TokenService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

  private static final int HOURS = 2;
  private static final String UTC_OFFSET = "-03:00";

  @Value("${akmarket.app.jwt.secret}")
  private String secret;

  @Value("${akmarket.app.jwt.issuer}")
  private String issuer;

  @Override
  public String gerarToken(User user) {
    try {
      return JWT.create()
          .withIssuer(issuer)
          .withSubject(user.getUsuario())
          .withExpiresAt(getDataExpiracao())
          .sign(Algorithm.HMAC256(secret));
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Erro ao gerar token JWT", exception);
    }
  }

  @Override
  public String getSubject(String jwtToken) {
    try {
     return JWT.require(Algorithm.HMAC256(secret))
         .withIssuer(issuer)
         .build()
         .verify(jwtToken)
         .getSubject();
    } catch (JWTVerificationException exception) {
       throw new RuntimeException("Token inválido! " + exception);
    }
  }

  private Instant getDataExpiracao() {
    return LocalDateTime.now().plusHours(HOURS).toInstant(ZoneOffset.of(UTC_OFFSET));
  }

}
