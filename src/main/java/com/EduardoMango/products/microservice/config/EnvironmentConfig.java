package com.EduardoMango.products.microservice.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class EnvironmentConfig {

    static {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("spring.datasource.url", Objects.requireNonNull(dotenv.get("DATABASE_URI")));
    }

    @Bean
    public Dotenv dotenv() {
        return Dotenv.load();
    }

}
