package com.bernardo.desafio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class Seed implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
    }
}
