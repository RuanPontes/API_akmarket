package com.example.testegpt.infrastructure.exception;

import com.example.testegpt.infrastructure.exception.response.ErroCampo;
import com.example.testegpt.infrastructure.exception.response.ErroCampoResponse;
import com.example.testegpt.infrastructure.exception.response.ErroResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErroResponse> handleFieldErrors(MethodArgumentNotValidException exception) {
    List<ErroCampo> erros = exception.getFieldErrors().stream().map(ErroCampo::new).toList();
    logger.error(exception.getLocalizedMessage(), exception.getCause());

    return ResponseEntity.badRequest()
        .body(new ErroCampoResponse(ExceptionConstantes.FIELD_ERROR_MESSAGE, erros));
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErroResponse> handleEntityNotFound(EntityNotFoundException exception) {
    logger.error(exception.getLocalizedMessage(), exception.getCause());

    return ResponseEntity.badRequest().body(new ErroResponse(exception));
  }

  @ExceptionHandler(TokenException.class)
  public ResponseEntity<ErroResponse> handleTokenErrors(TokenException exception) {
    logger.error(exception.getLocalizedMessage(), exception.getCause());

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErroResponse(exception));
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErroResponse> handleUserAlreadyExistsError(
      UserAlreadyExistsException exception) {
    logger.error(exception.getLocalizedMessage(), exception.getCause());

    return ResponseEntity.badRequest().body(new ErroResponse(exception));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErroResponse> handleException(Exception exception) {
    logger.error(exception.getLocalizedMessage(), exception.getCause());

    return ResponseEntity.internalServerError().body(new ErroResponse(ExceptionConstantes.GENERIC_ERROR));
  }
}
