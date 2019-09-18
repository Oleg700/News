package com.epam.news.controller;

import com.epam.news.config.AppConfig;
import com.epam.news.model.news.News;
import com.epam.news.service.news.NewsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class NewsControllerTest {

    private static final Logger LOGGER = Logger.getLogger(NewsControllerTest.class.getName());

    @Autowired
    NewsService newsService;

    @Test
    void getAllNews() {
        Collection<News> newsList = newsService.getAll();
       LOGGER.info("newsList: "+newsList.toString());
       assertFalse(newsList.isEmpty());
    }

    @Test
    void getNewsById() {
    }

    @Test
    void addNews() {
    }

    @Test
    void updateNews() {
    }

    @Test
    void deleteNews() {
    }
}