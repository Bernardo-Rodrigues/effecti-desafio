package com.bernardo.desafio.services.impl;

import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.entities.User;
import com.bernardo.desafio.model.exception.ConflictException;
import com.bernardo.desafio.model.mapper.UserMapper;
import com.bernardo.desafio.repositories.UserRepository;
import com.bernardo.desafio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDto create(UserDto dto) {
        User alreadyExist = userRepository.findByName(dto.getName());
        if(alreadyExist != null) throw new ConflictException("User already exist");

        User entity = userMapper.dtoToEntity(dto);
        entity = userRepository.save(entity);
        return userMapper.entityToDto(entity);
    }
}
