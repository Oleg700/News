package com.epam.news;

import com.epam.news.dao.implementation.NewsDaoImpl;
import com.epam.news.model.News;
import com.epam.news.service.NewsServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        getAll();
    }

    public static void delete() {
        NewsServiceImpl newsService = new NewsServiceImpl(new NewsDaoImpl());
        newsService.delete(3);
    }

    public static void update() {
        News news = new News("Los-angeles", LocalDateTime.now(), "brief note", "content text");
        NewsServiceImpl newsService = new NewsServiceImpl(new NewsDaoImpl());
        newsService.update(2, news);
    }
    public static void getAll() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        NewsServiceImpl newsService = (NewsServiceImpl) context.getBean("NewsServiceImplBean");
        List list = newsService.getAll();
        list.forEach(news -> System.out.println(news));
    }


}


