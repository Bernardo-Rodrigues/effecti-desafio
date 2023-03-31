package com.bernardo.desafio.services.impl;

import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.entities.User;
import com.bernardo.desafio.model.exception.BadRequestException;
import com.bernardo.desafio.model.exception.NotFoundException;
import com.bernardo.desafio.model.exception.UnauthorizedException;
import com.bernardo.desafio.model.mapper.UserMapper;
import com.bernardo.desafio.repositories.UserRepository;
import com.bernardo.desafio.services.JwtService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    Environment environment;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public String generateToken(String name) {
        Map<String, Object> claims = new HashMap<>();

        User user = userRepository.findByName(name);
        if(user == null) throw new NotFoundException("User does not exist");

        return createToken(claims, name);
    }

    @Override
    public UserDto validateToken(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(environment.getProperty("jwtsecret"));

        try {
            Claims claims = parser.parseClaimsJws(token).getBody();
            UserDto dto = userMapper.entityToDto(userRepository.findByName(claims.getSubject()));
            if(dto == null) throw new UnauthorizedException("JWT invalid user");
            return dto;
        } catch (SignatureException e) {
            throw new UnauthorizedException("JWT invalid signature");
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("JWT expired");
        } catch (MalformedJwtException e){
            throw new BadRequestException("Wrong format of JWT");
        } catch (JsonParseException e){
            throw new BadRequestException("Wrong format of JWT");
        }
    }

    private String createToken(Map<String, Object> claims, String subject) {
        String SECRET_KEY = environment.getProperty("jwtsecret");
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

}