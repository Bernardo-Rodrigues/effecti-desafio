package com.bernardo.desafio.repositories;

import com.bernardo.desafio.model.entities.Bid;
import com.bernardo.desafio.model.interfaces.Read;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Integer> {

    @Query(value = "SELECT CASE WHEN r.user_id IS NOT NULL THEN true ELSE false END AS read " +
            "FROM bids b " +
            "LEFT JOIN readings r ON r.bid_id = b.id AND r.user_id = ?1",
            nativeQuery = true)
    List<Read> findAllWithReadProperty(Integer userId);
}
