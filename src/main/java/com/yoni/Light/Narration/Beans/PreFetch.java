package com.yoni.Light.Narration.Beans;

import com.yoni.Light.Narration.Client.OpenLibClient;
import com.yoni.Light.Narration.Repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PreFetch {

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(BookRepo bookRepo){
        return args -> {
            bookRepo.saveAll(new OpenLibClient().callApi());
        };
    }
}
