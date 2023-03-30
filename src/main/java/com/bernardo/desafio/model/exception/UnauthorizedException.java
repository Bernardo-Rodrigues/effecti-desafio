package com.bernardo.desafio.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnauthorizedException extends RuntimeException {

    HttpStatus status = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException(String reason) {
        super(reason);
    }
}
