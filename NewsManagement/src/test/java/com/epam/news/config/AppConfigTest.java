package com.epam.news.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@PropertySource("classpath:db.test.properties")
@EnableTransactionManagement
@ComponentScans(value = {
        @ComponentScan("com.epam.news")
})
class AppConfigTest {
}



