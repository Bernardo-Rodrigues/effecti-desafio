package com.bernardo.desafio.model.mapper;

import com.bernardo.desafio.model.dto.BidDto;
import com.bernardo.desafio.model.entities.Bid;
import com.bernardo.desafio.model.reponse.BidResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BidMapper {
    List<BidDto> entityListToDtoList(List<Bid> entityList);
    List<BidResponse> dtoListToResponseList(List<BidDto> dtoList);
}
