package com.epam.news;

import com.epam.news.service.NewsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String args[]) {
        getAll();
    }

    public static void getAll(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        NewsService newsService = (NewsService) applicationContext.getBean("newsServiceImpl");
        System.out.println(newsService.getAll());
    }

}


