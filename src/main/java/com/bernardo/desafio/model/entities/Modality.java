package com.bernardo.desafio.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "modalities")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Modality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @JsonIgnore
    @OneToMany(mappedBy = "modality", cascade = CascadeType.ALL)
    List<Bid> bids = new ArrayList<>();
}
