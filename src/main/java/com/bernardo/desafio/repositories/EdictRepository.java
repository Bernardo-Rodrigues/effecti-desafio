package com.bernardo.desafio.repositories;

import com.bernardo.desafio.model.entities.Edict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdictRepository extends JpaRepository<Edict, Integer> {
}
