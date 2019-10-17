package com.epam.news.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
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


    /**
     * is used to get objects from MvcResult in integrations tests.
     *
     * @return object mapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}



