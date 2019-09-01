package com.epam.news.service;

import com.epam.news.dao.NewsDao;
import com.epam.news.model.News;

import java.util.List;


public class NewsServiceImpl implements NewsService {

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
    public News add(News news) {
        return newsDao.add(news);
    }

    @Override
    public News update(News news) {
        return newsDao.update(news);
    }

    @Override
    public void delete(int id) {
        newsDao.delete(id);
    }
}
