package com.bernardo.desafio.controllers;

import com.bernardo.desafio.model.dto.BidDto;
import com.bernardo.desafio.model.mapper.BidMapper;
import com.bernardo.desafio.model.reponse.BidResponse;
import com.bernardo.desafio.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    BidService bidService;
    @Autowired
    BidMapper bidMapper;

    @GetMapping
    public ResponseEntity<List<BidResponse>> list(){
        List<BidDto> dtoList = bidService.list();
        return ResponseEntity.ok().body(bidMapper.dtoListToResponseList(dtoList));
    }
}
