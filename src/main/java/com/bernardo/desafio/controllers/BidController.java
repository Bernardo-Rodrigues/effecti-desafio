package com.bernardo.desafio.controllers;

import com.bernardo.desafio.model.dto.BidDto;
import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.mapper.BidMapper;
import com.bernardo.desafio.model.reponse.BidResponse;
import com.bernardo.desafio.services.BidService;
import com.bernardo.desafio.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    BidService bidService;
    @Autowired
    JwtService jwtService;
    @Autowired
    BidMapper bidMapper;

    @GetMapping
    public ResponseEntity<List<BidResponse>> list(@RequestHeader(value = "jwt") String jwt){
        UserDto userDto = jwtService.validateToken(jwt);
        List<BidDto> dtoList = bidService.list(userDto.getId());
        return ResponseEntity.ok().body(bidMapper.dtoListToResponseList(dtoList));
    }

    @PostMapping("/{id}/read")
    public ResponseEntity read(
            @RequestHeader(value = "jwt") String jwt,
            @PathVariable Integer id
    ){
        UserDto userDto = jwtService.validateToken(jwt);
        bidService.read(userDto, id);
        return ResponseEntity.ok().build();
    }
}
