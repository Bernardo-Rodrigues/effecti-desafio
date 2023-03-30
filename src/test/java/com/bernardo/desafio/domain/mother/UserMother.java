package com.bernardo.desafio.domain.mother;

import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.request.CreateUserRequest;

public class UserMother {

    public static CreateUserRequest getUserRegistrationRequest(){
        return CreateUserRequest.builder()
                .name("user-test-2")
                .build();
    }

    public static UserDto getUserDto(){
        return UserDto.builder()
                .id(1)
                .name("user-test-1")
                .build();
    }
}
