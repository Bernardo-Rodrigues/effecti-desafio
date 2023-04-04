package com.bernardo.desafio.model.entities;

import com.bernardo.desafio.model.enums.Modality;
import com.bernardo.desafio.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

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
    @Enumerated(EnumType.STRING)
    Modality modality;
    String name;
    String link;
    @Enumerated(EnumType.STRING)
    Status status;
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
