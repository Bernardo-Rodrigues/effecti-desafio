package com.bernardo.desafio.controllers;

import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.mapper.UserMapper;
import com.bernardo.desafio.model.reponse.AuthenticateUserResponse;
import com.bernardo.desafio.model.request.CreateUserRequest;
import com.bernardo.desafio.services.JwtService;
import com.bernardo.desafio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserMapper userMapper;

    @PostMapping
    ResponseEntity create(@RequestBody CreateUserRequest body){
        UserDto dto = userService.create(userMapper.requestToDto(body));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{name}/authenticate")
    ResponseEntity<AuthenticateUserResponse> authenticate(@PathVariable String name){
        String token = jwtService.generateToken(name);

        return ResponseEntity.ok().body(AuthenticateUserResponse.builder().token(token).build());
    }
}
