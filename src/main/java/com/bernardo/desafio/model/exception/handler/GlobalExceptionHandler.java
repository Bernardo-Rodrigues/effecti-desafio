package com.bernardo.desafio.model.exception.handler;

import java.time.Instant;

import com.bernardo.desafio.model.exception.*;
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

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(NotFoundException e, HttpServletRequest request) {
        String error = "Entity not found";
        StandardError err = new StandardError(Instant.now(), e.getStatus().value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(e.getStatus()).body(err);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequest(BadRequestException e, HttpServletRequest request) {
        String error = "Bad request";
        StandardError err = new StandardError(Instant.now(), e.getStatus().value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(e.getStatus()).body(err);
    }
}
