package com.epam.news.dao;

import com.epam.news.model.News;

import java.util.List;

public interface NewsDao {
    List<News> getAll();

    News get(long id);

    News add(News news);

    News update(News news);

    void delete(long id);
}
