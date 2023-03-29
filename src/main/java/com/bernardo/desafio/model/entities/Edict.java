package com.bernardo.desafio.model.entities;

import com.bernardo.desafio.model.entities.Bid;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "edicts")
@Data
@Builder
public class Edict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String link;
    @ManyToOne
    @JoinColumn(name = "bidId")
    Bid bid;
}
