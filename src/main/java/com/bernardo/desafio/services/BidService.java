package com.bernardo.desafio.services;

import com.bernardo.desafio.model.dto.BidDto;
import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.enums.Modality;

import java.util.List;

public interface BidService {
    List<BidDto> list(Integer userId, Modality modality);
    void read(UserDto userDto, Integer bidId);
}
