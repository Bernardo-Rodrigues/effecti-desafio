package com.bernardo.desafio.repositories;

import com.bernardo.desafio.model.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Integer> {
}
