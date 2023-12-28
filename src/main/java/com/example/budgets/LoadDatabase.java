package com.example.budgets;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BudgetsRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Budget("December")));
        };
    }
}
