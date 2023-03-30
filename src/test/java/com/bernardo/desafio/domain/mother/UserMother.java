package com.bernardo.desafio.domain.mother;

import com.bernardo.desafio.model.request.CreateUserRequest;

public class UserMother {

    public static CreateUserRequest getUserRegistrationRequest(){
        return CreateUserRequest.builder()
                .name("user")
                .build();
    }
}
