package com.bernardo.desafio.model.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    HttpStatus status = HttpStatus.UNAUTHORIZED;

    public BadRequestException(String reason) {
        super(reason);
    }
}
