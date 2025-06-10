package com.EduardoMango.products.microservice.config;

import com.EduardoMango.products.microservice.model.documents.Product;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.Objects;

@Configuration
@EnableSpringDataWebSupport
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
