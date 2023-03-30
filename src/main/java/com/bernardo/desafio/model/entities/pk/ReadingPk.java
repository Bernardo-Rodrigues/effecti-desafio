package com.bernardo.desafio.model.entities.pk;

import com.bernardo.desafio.model.entities.Bid;
import com.bernardo.desafio.model.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadingPk {
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bidId")
    private Bid bid;
}
