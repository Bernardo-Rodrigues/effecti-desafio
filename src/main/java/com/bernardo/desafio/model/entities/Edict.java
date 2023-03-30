package com.bernardo.desafio.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "edicts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Edict {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String link;
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bidId")
    Bid bid;
}
