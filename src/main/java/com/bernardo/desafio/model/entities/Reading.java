package com.bernardo.desafio.model.entities;

import com.bernardo.desafio.model.entities.pk.ReadingPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "readings")
@Data
@Builder
public class Reading {

    @EmbeddedId
    private ReadingPk id;
}
