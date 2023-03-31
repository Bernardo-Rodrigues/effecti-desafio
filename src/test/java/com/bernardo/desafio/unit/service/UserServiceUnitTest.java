package com.bernardo.desafio.unit.service;

import com.bernardo.desafio.domain.mother.UserMother;
import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.entities.User;
import com.bernardo.desafio.model.exception.ConflictException;
import com.bernardo.desafio.model.mapper.UserMapper;
import com.bernardo.desafio.repositories.UserRepository;
import com.bernardo.desafio.services.impl.UserServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;


@ExtendWith({MockitoExtension.class})
public class UserServiceUnitTest implements WithAssertions {

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;

    @Test
    void givenAnAttemptToCreateAnUserWhenAlreadyExistOneWithTheGivenNameThenThrowConflictError(){
        UserDto userDto = UserMother.getUserDto();
        User user = UserMother.getUser();

        given(userRepository.findByName(userDto.getName())).willReturn(user);

        assertThatThrownBy(() -> userService.create(userDto)).isInstanceOf(ConflictException.class);


    }
}
