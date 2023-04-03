package com.bernardo.desafio.unit.service;

import com.bernardo.desafio.domain.mother.UserMother;
import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.exception.NotFoundException;
import com.bernardo.desafio.repositories.UserRepository;
import com.bernardo.desafio.services.impl.JwtServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.NotActiveException;

import static org.mockito.BDDMockito.given;
@ExtendWith({MockitoExtension.class})
public class JwtServiceUnitTest implements WithAssertions {

    @InjectMocks
    JwtServiceImpl jwtService;
    @Mock
    UserRepository userRepository;

    @Test
    void givenAnAttemptToAuthenticateAnUserWhenTheUserDoesNotExistThenThrowNotFoundError(){
        UserDto userDto = UserMother.getUserDto();

        given(userRepository.findByName(userDto.getName())).willReturn(null);

        assertThatThrownBy(() -> jwtService.generateToken(userDto.getName())).isInstanceOf(NotFoundException.class);
    }
}
