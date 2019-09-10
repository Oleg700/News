package com.epam.news.service.news;

import com.epam.news.dao.news.NewsDao;
import com.epam.news.model.News;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class NewsServiceImpl implements NewsService {


    private NewsDao newsDao;

    @Autowired
    public NewsServiceImpl(NewsDao newsDao) {
        this.newsDao = newsDao;
    }


    @Override
    public List<News> getAll() {
        return newsDao.getAll();
    }

    @Override
    public News get(long id) {
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
    public void delete(long id) {
        newsDao.delete(id);
    }
}
