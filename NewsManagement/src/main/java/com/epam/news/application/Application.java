package com.epam.news.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        /*NewsDao newsDao = new NewsDaoImpl();
        newsDao.getAll();*/
    }
}