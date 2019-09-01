package com.epam.news.service;

import com.epam.news.dao.NewsDao;
import com.epam.news.model.News;

import java.util.List;


public class NewsServiceImpl implements NewsService<News> {

    private NewsDao newsDao;

    public NewsServiceImpl(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public List<News> getAll() {
        return newsDao.getAll();
    }

    @Override
    public News get(int id) {
        return (News) newsDao.get(id);
    }

    @Override
    public int add(News news) {
        return newsDao.add(news);
    }

    @Override
    public void update(News news) {
        newsDao.update(news);
    }

    @Override
    public void delete(int id) {
        newsDao.delete(id);
    }
}
