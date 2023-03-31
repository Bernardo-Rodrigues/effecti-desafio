package com.bernardo.desafio.services;

import com.bernardo.desafio.model.dto.BidDto;
import com.bernardo.desafio.model.dto.UserDto;

import java.util.List;

public interface BidService {
    List<BidDto> list(Integer userId);
    void read(UserDto userDto, Integer bidId);
}
