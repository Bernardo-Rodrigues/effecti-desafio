package com.bernardo.desafio.config;

import com.bernardo.desafio.model.entities.Bid;
import com.bernardo.desafio.model.entities.User;
import com.bernardo.desafio.repositories.BidRepository;
import com.bernardo.desafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class Seed implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BidRepository bidRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder().name("user-test-1").build();
        userRepository.save(user);

        Bid bid = Bid.builder().build();
        bidRepository.save(bid);
    }
}
