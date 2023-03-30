package com.bernardo.desafio.config;

import com.bernardo.desafio.model.entities.Modality;
import com.bernardo.desafio.repositories.ModalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@Profile("prod")
public class Seed implements CommandLineRunner {

    @Autowired
    ModalityRepository modalityRepository;

    @Override
    public void run(String... args) throws Exception {
        Modality modality_1 = Modality.builder().name("Chamada Pública").bids(new ArrayList<>()).build();
        Modality modality_2 = Modality.builder().name("Chamamento Público").bids(new ArrayList<>()).build();
        Modality modality_3 = Modality.builder().name("Concorrência Pública").bids(new ArrayList<>()).build();
        Modality modality_4 = Modality.builder().name("Concurso").bids(new ArrayList<>()).build();
        Modality modality_5 = Modality.builder().name("Convite").bids(new ArrayList<>()).build();
        Modality modality_6 = Modality.builder().name("Credenciamento").bids(new ArrayList<>()).build();
        Modality modality_7 = Modality.builder().name("Dispensa").bids(new ArrayList<>()).build();
        Modality modality_8 = Modality.builder().name("Inexigibilidade").bids(new ArrayList<>()).build();
        Modality modality_9 = Modality.builder().name("Leilão").bids(new ArrayList<>()).build();
        Modality modality_10 = Modality.builder().name("Pregão").bids(new ArrayList<>()).build();
        Modality modality_11 = Modality.builder().name("RDC").bids(new ArrayList<>()).build();
        Modality modality_12 = Modality.builder().name("Tomada de Preços").bids(new ArrayList<>()).build();

        modalityRepository.saveAll(Arrays.asList(modality_1, modality_2, modality_3, modality_4, modality_5,
                modality_6, modality_7, modality_8, modality_9, modality_10, modality_11, modality_12));
    }
}
