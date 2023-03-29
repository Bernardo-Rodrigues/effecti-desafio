package com.bernardo.desafio.repositories;

import com.bernardo.desafio.model.entities.Reading;
import com.bernardo.desafio.model.entities.pk.ReadingPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<Reading, ReadingPk> {
}
