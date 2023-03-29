package com.bernardo.desafio.model.entities.pk;

import com.bernardo.desafio.model.entities.Bid;
import com.bernardo.desafio.model.entities.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class ReadingPk {
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bidId")
    private Bid bid;
}
