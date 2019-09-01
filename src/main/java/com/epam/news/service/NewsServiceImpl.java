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
        int id = newsDao.add(news);
        return id;
    }

    @Override
    public void update(int id, News news) {
        newsDao.update(id, news);
    }

    @Override
    public void delete(int id) {
        newsDao.delete(id);

    }
}
