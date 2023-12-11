package com.example.testegpt.infrastructure.exception;

import com.example.testegpt.infrastructure.TokenException;
import com.example.testegpt.infrastructure.exception.response.ErroCampo;
import com.example.testegpt.infrastructure.exception.response.ErroCampoResponse;
import com.example.testegpt.infrastructure.exception.response.ErroResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErroResponse> handleFieldErrors(MethodArgumentNotValidException exception) {
    List<ErroCampo> erros = exception.getFieldErrors().stream().map(ErroCampo::new).toList();
    return ResponseEntity.badRequest()
        .body(
            new ErroCampoResponse(
                ExceptionConstantes.FIELD_ERROR_MESSAGE, LocalDateTime.now().toString(), erros));
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErroResponse> handleEntityNotFound(EntityNotFoundException exception) {
    return ResponseEntity.badRequest()
        .body(new ErroResponse(exception.getMessage(), LocalDateTime.now().toString()));
  }

  @ExceptionHandler(TokenException.class)
  public ResponseEntity<ErroResponse> handleTokenErrors(TokenException exception) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new ErroResponse(exception.getMessage(), LocalDateTime.now().toString()));
  }
}
