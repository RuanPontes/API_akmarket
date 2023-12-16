package com.example.testegpt.infrastructure.security.filter;

import static com.example.testegpt.infrastructure.exception.ExceptionConstantes.TOKEN_NAO_VALIDADO;

import com.example.testegpt.constants.Headers;
import com.example.testegpt.domain.User;
import com.example.testegpt.infrastructure.exception.response.ErroResponse;
import com.example.testegpt.service.TokenService;
import com.example.testegpt.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  private final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
  private final UserService userService;
  private final TokenService tokenService;

  public SecurityFilter(UserService userService, TokenService tokenService) {
    this.userService = userService;
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException {
    try {
      String token = request.getHeader(Headers.AUTHORIZATION);

      if (Objects.nonNull(token)) {
        User user = userService.findByUsuario(tokenService.getSubject(token));

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }

      filterChain.doFilter(request, response);
    } catch (Exception e) {
      logger.error(TOKEN_NAO_VALIDADO);

      ErroResponse erroResponse = new ErroResponse(TOKEN_NAO_VALIDADO);
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write(new ObjectMapper().writeValueAsString(erroResponse));
    }
  }

}
