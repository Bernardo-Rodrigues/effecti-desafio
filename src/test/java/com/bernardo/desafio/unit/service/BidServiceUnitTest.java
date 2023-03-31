package com.bernardo.desafio.unit.service;

import com.bernardo.desafio.domain.mother.BidMother;
import com.bernardo.desafio.domain.mother.UserMother;
import com.bernardo.desafio.model.dto.BidDto;
import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.exception.NotFoundException;
import com.bernardo.desafio.repositories.BidRepository;
import com.bernardo.desafio.services.impl.BidServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class BidServiceUnitTest implements WithAssertions {

    @InjectMocks
    BidServiceImpl bidService;
    @Mock
    BidRepository bidRepository;

    @Test
    void givenAnAttemptToReadABidWhenTheGivenBidIdNotExistThenThrowNotFoundError(){
        BidDto bidDto = BidMother.getBidDto();
        UserDto userDto = UserMother.getUserDto();

        given(bidRepository.findById(bidDto.getId())).willReturn(Optional.empty());

        assertThatThrownBy(() -> bidService.read(userDto, bidDto.getId())).isInstanceOf(NotFoundException.class);
    }
}
