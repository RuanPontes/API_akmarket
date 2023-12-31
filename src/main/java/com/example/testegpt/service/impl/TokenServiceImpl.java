package com.example.testegpt.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.testegpt.domain.User;
import com.example.testegpt.infrastructure.exception.TokenException;
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
  private static final String USER_ID = "userId";

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
          .withClaim(USER_ID, user.getId())
          .withExpiresAt(getDataExpiracao())
          .sign(Algorithm.HMAC256(secret));
    } catch (JWTCreationException exception) {
      throw new TokenException("Erro ao gerar token JWT", exception);
    }
  }

  @Override
  public String getSubject(String jwtToken) {
    try {
     return JWT.require(Algorithm.HMAC256(secret))
         .withIssuer(issuer)
         .build()
         .verify(removeBearer(jwtToken))
         .getSubject();
    } catch (JWTVerificationException exception) {
       throw new TokenException("Não foi possível validar o token", exception);
    }
  }

  private Instant getDataExpiracao() {
    return LocalDateTime.now().plusHours(HOURS).toInstant(ZoneOffset.of(UTC_OFFSET));
  }

  private String removeBearer(String token) {
    return token.replace("Bearer ", "").trim();
  }

}
