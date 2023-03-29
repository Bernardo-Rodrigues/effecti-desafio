package com.bernardo.desafio.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "bids")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name = "modalityId")
    Modality modality;
    String name;
    LocalDate openingDate;
    @Column(columnDefinition = "text")
    String description;
    String entity;
    String sector;
    @Column(columnDefinition = "text")
    String local;
    Double value;
    @OneToMany(mappedBy = "bid", cascade = CascadeType.ALL)
    List<Edict> edicts;
}
