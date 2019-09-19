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
    private static final String NEWS_TITLE = "title_test";
    private static final String NEWS_BRIEF = "brief_test";
    private static final String NEWS_CONTENT = "content_test";
    private static final int NEWS_ID = 11;
    private static final int NEWS_ID_DELETE = 13;

    @Autowired
    NewsService newsService;

    @Test
    void getAllNews() {
        Collection<News> newsList = newsService.getAll();
       LOGGER.info("newsList: "+newsList.toString());
       assertFalse(newsList.isEmpty());
    }

    @Test
    void getNewsWithTwoRecentComments(){
        News news = newsService.getNewsWithTwoRecentComments(11, 8);
        System.out.println(news.getComments());
        assertNotNull(news);
    }

    @Test
    void getNewsById() {
        News news = newsService.get(NEWS_ID);
        assertNotNull(news);
    }

    @Test
    void addNews() {
        News news = new News(NEWS_TITLE, NEWS_BRIEF, NEWS_CONTENT);
        News newsResult = newsService.add(news);
        assertNotNull(newsResult);
    }

    @Test
    void updateNews() {
        News news = newsService.get(NEWS_ID);
        news.setTitle("Longon");
        News newsResult = newsService.update(news);
        assertNotNull(newsResult);
    }

    @Test
    void deleteNews() {
        newsService.delete(NEWS_ID_DELETE);
        News news = newsService.get(NEWS_ID_DELETE);
        assertNull(news);

    }
}