package com.epam.news.dao.implementation;

import com.epam.news.config.AppConfig;
import com.epam.news.dao.news.NewsDao;
import com.epam.news.model.news.News;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitWebConfig(classes = {AppConfig.class, NewsWebAppInitializer.class, WebConfig.class})*/


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class NewsDaoImplTest {

    @Autowired
    NewsDao newsDao;

    @Test
    void getAll() {
        List<News> newsList = newsDao.getAll();
        assertFalse(newsList.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void get(int id) {
        News news = (News) newsDao.get(id);
        assertNotNull(news);
    }

    @Test
    void add() {
        News news = new News("Praga", LocalDateTime.now(), "brief note", "content text");
        News result = newsDao.add(news);
        assertNotNull( result);
    }


    @Test
    void update() {
        News news = new News(1, "Singapur", LocalDateTime.now(), "brief note", "content about Singapur");
        newsDao.update(news);
        News updatedNews = (News) newsDao.get(1);
        assertEquals(news, updatedNews);
    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    void delete(int id) {
        newsDao.delete(id);
        News news = (News) newsDao.get(id);
        assertNull(news);
    }
}