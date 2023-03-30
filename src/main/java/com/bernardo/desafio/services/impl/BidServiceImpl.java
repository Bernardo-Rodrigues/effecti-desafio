package com.bernardo.desafio.services.impl;

import com.bernardo.desafio.model.dto.BidDto;
import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.entities.Bid;
import com.bernardo.desafio.model.entities.Reading;
import com.bernardo.desafio.model.entities.User;
import com.bernardo.desafio.model.entities.pk.ReadingPk;
import com.bernardo.desafio.model.exception.ConflictException;
import com.bernardo.desafio.model.exception.NotFoundException;
import com.bernardo.desafio.model.mapper.BidMapper;
import com.bernardo.desafio.model.mapper.UserMapper;
import com.bernardo.desafio.repositories.BidRepository;
import com.bernardo.desafio.repositories.ReadingRepository;
import com.bernardo.desafio.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    BidRepository bidRepository;
    @Autowired
    ReadingRepository readingRepository;
    @Autowired
    BidMapper bidMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public List<BidDto> list() {
        return bidMapper.entityListToDtoList(bidRepository.findAll());
    }

    @Override
    public void read(UserDto userDto, Integer bidId) {
        Bid bid = bidRepository.findById(bidId).orElseThrow(() -> new NotFoundException("Bid not found"));

        Reading entity = Reading.builder()
                .id(ReadingPk.builder()
                        .bid(bid)
                        .user(userMapper.dtoToEntity(userDto))
                        .build()
                ).build();

        readingRepository.save(entity);
    }
}
