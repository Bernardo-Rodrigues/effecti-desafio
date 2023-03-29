package com.bernardo.desafio.model.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bids")
@Data
@Builder
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String portal;
    Date openingDate;
    String description;
    String entity;
    String sector;
    String local;
    Double value;
    @OneToMany(mappedBy = "bid", cascade = CascadeType.ALL)
    List<Edict> edicts;
}
