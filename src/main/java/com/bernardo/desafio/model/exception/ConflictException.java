package com.bernardo.desafio.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ConflictException extends RuntimeException {

    HttpStatus status = HttpStatus.UNAUTHORIZED;

    public ConflictException(String reason) {
        super(reason);
    }
}