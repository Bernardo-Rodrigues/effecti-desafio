package com.bernardo.desafio.services;

import com.bernardo.desafio.model.dto.UserDto;

public interface JwtService {

    String generateToken(String name);

    UserDto validateToken(String token);
}
