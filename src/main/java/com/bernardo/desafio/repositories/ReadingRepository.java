package com.bernardo.desafio.repositories;

import com.bernardo.desafio.model.entities.Reading;
import com.bernardo.desafio.model.entities.pk.ReadingPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReadingRepository extends JpaRepository<Reading, ReadingPk> {
    @Query(value = "SELECT * " +
            "FROM readings r " +
            "WHERE r.user_id = ?1 AND r.bid_id = ?2",
            nativeQuery = true)
    Reading findByUserIdAndBidId(Integer userId, Integer BidId);
}
