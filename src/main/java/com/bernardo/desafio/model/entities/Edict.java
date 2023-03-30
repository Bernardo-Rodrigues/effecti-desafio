package com.bernardo.desafio.model.entities;

import com.bernardo.desafio.model.entities.Bid;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "edicts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Edict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String link;
    @ManyToOne
    @JoinColumn(name = "bidId")
    Bid bid;
}
