package com.epam.news.dao.implementation;

import com.epam.news.model.News;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsDaoImplTest {

    @Test
    void getAll() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        NewsDaoImpl newsDaoImpl = (NewsDaoImpl) context.getBean("newsDaoImpl");
        List<News> newsList = newsDaoImpl.getAll();
        System.out.println(newsList);
        assertFalse(newsList.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void get(int id) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        NewsDaoImpl newsDaoImpl = (NewsDaoImpl) context.getBean("newsDaoImpl");
        News news = (News) newsDaoImpl.get(id);
        assertNotNull(news);
    }

    @Test
    void add() {
        News news = new News("Praga", LocalDateTime.now(), "brief note", "content text");
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        NewsDaoImpl newsDaoImpl = (NewsDaoImpl) context.getBean("newsDaoImpl");
        int result = newsDaoImpl.add(news);
        assertEquals(0, result);
    }

    //needs to be fixed
    @Test
    @Ignore
    void update() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        NewsDaoImpl newsDaoImpl = (NewsDaoImpl) context.getBean("newsDaoImpl");
        News news = new News(1, "Singapur", LocalDateTime.now(), "brief note", "content about Singapur");
        newsDaoImpl.update(news);
        News updatedNews = (News) newsDaoImpl.get(1);
        assertEquals(news, updatedNews);
    }

    @ParameterizedTest
    @ValueSource(ints = {21})
    void delete(int id) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        NewsDaoImpl newsDaoImpl = (NewsDaoImpl) context.getBean("newsDaoImpl");
        newsDaoImpl.delete(id);
        News news = (News) newsDaoImpl.get(id);
        assertNull(news);
    }
}