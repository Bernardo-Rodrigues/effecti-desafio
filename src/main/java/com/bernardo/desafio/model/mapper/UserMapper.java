package com.bernardo.desafio.model.mapper;

import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.entities.User;
import com.bernardo.desafio.model.request.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User dtoToEntity(UserDto dto);
    UserDto entityToDto(User entity);
    UserDto requestToDto(CreateUserRequest request);
}
