package com.bernardo.desafio.model.exception.handler;

import java.time.Instant;

import com.bernardo.desafio.model.exception.ConflictException;
import com.bernardo.desafio.model.exception.StandardError;
import com.bernardo.desafio.model.exception.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<StandardError> conflict(ConflictException e, HttpServletRequest request) {
        String error = "Information already exists";
        StandardError err = new StandardError(Instant.now(), e.getStatus().value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(e.getStatus()).body(err);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<StandardError> unauthorizedAccess(UnauthorizedException e, HttpServletRequest request) {
        String error = "Unauthorized access";
        StandardError err = new StandardError(Instant.now(), e.getStatus().value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(e.getStatus()).body(err);
    }
}
