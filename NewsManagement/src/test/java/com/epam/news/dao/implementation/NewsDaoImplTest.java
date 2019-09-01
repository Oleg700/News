package com.epam.news.dao.implementation;

import com.epam.news.model.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsDaoImplTest {

    private ApplicationContext context;
    private NewsDaoImpl newsDaoImpl;

    @Test
    @BeforeEach
    void init(){
        context = new ClassPathXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        newsDaoImpl = (NewsDaoImpl) context.getBean("newsDaoImpl");
    }

    @Test
    void getAll() {
        List<News> newsList = newsDaoImpl.getAll();
        assertFalse(newsList.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void get(int id) {
        News news = (News) newsDaoImpl.get(id);
        assertNotNull(news);
    }

    @Test
    void add() {
        News news = new News("Praga", LocalDateTime.now(), "brief note", "content text");
        News result = newsDaoImpl.add(news);
        assertEquals(0, result);
    }


    @Test
    void update() {
        News news = new News(1, "Singapur", LocalDateTime.now(), "brief note", "content about Singapur");
        newsDaoImpl.update(news);
        News updatedNews = (News) newsDaoImpl.get(1);
        assertEquals(news, updatedNews);
    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    void delete(int id) {
        newsDaoImpl.delete(id);
        News news = (News) newsDaoImpl.get(id);
        assertNull(news);
    }
}