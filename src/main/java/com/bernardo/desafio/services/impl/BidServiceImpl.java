package com.bernardo.desafio.services.impl;

import com.bernardo.desafio.model.dto.BidDto;
import com.bernardo.desafio.model.mapper.BidMapper;
import com.bernardo.desafio.repositories.BidRepository;
import com.bernardo.desafio.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    BidRepository bidRepository;
    @Autowired
    BidMapper bidMapper;


    @Override
    public List<BidDto> list() {
        return bidMapper.entityListToDtoList(bidRepository.findAll());
    }
}
