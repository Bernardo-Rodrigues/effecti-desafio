package com.bernardo.desafio.model.dto;

import com.bernardo.desafio.model.entities.Edict;
import com.bernardo.desafio.model.entities.Modality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidDto {
    Integer id;
    Modality modality;
    String name;
    String link;
    LocalDate openingDate;
    String description;
    String entity;
    String sector;
    String local;
    Double value;
    List<Edict> edicts;
    Boolean read;
}
