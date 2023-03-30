package com.bernardo.desafio.model.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    HttpStatus status = HttpStatus.UNAUTHORIZED;

    public NotFoundException(String reason) {
        super(reason);
    }
}
