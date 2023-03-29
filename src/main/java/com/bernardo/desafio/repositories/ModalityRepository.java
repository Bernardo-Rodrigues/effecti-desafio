package com.bernardo.desafio.repositories;

import com.bernardo.desafio.model.entities.Modality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalityRepository extends JpaRepository<Modality, Integer> {
    public Modality findByName(String name);
}
