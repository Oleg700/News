package com.epam.news.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Implementation of {@link WebMvcConfigurer}.
 *
 * @author Oleg Aliyev
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.epam.news.controller"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig implements WebMvcConfigurer {
}
