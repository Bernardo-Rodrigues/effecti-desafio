package com.bernardo.desafio.model.reponse;

import com.bernardo.desafio.model.entities.Edict;
import com.bernardo.desafio.model.entities.Modality;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BidResponse {
    @JsonProperty("id")
    Integer id;
    @JsonProperty("modalidade")
    Modality modality;
    @JsonProperty("nome")
    String name;
    @JsonProperty("dataDeAbertura")
    LocalDate openingDate;
    @JsonProperty("descricao")
    String description;
    @JsonProperty("entidade")
    String entity;
    @JsonProperty("setor")
    String sector;
    @JsonProperty("local")
    String local;
    @JsonProperty("valorGlobal")
    Double value;
    @JsonProperty("editais")
    List<Edict> edicts;
}
