package com.bernardo.desafio.model.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String reason) {
        super(reason);
    }
}
