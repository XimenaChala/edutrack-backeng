package com.edutrack.communication.infrastructure.web;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.edutrack.communication.domain.exception.InvalidMessageException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidMessageException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidMessage(InvalidMessageException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "Bad Request",
                "message", ex.getMessage(),
                "timestamp", Instant.now().toString()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("timestamp", Instant.now().toString());
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("error", "Bad Request");
        // Obtener el primer mensaje de error de la validación
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        errors.put("message", message);
        return ResponseEntity.badRequest().body(errors);
    }
}