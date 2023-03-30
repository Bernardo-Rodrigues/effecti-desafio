package com.bernardo.desafio.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "modalities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Modality {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "modality", cascade = CascadeType.ALL)
    List<Bid> bids = new ArrayList<>();
}
