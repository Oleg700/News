package com.epam.news.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.epam.news")
@EnableJpaRepositories("com.epam.news.repository")
@EntityScan("com.epam.news.model")
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
