package com.bernardo.desafio.domain.mother;

import com.bernardo.desafio.model.dto.BidDto;
import com.bernardo.desafio.model.dto.UserDto;

public class BidMother {
    public static BidDto getBidDto(){
        return BidDto.builder()
                .id(1)
                .build();
    }
}
