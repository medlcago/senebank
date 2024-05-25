package org.backend.senebank.controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.backend.senebank.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Контроллер для перехвата исключений
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionController {
    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("message", ex.getMessage());

        HttpStatus status;
        if (ex instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;
        } else if (ex instanceof EntityNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof InsufficientFundsException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof InvalidLoginCredentialsException) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex instanceof UserAlreadyExistsException) {
            status = HttpStatus.CONFLICT;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> methodNotAllowed() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> notFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.internalServerError().build();
    }
}
