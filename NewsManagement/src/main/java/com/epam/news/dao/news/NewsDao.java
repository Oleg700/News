package com.epam.news.dao.news;

import com.epam.news.model.news.News;

import java.util.List;

public interface NewsDao {


    List<News> getAll();

    News get(long id);

    News add(News news);

    News update(News news);

    void delete(long id);
}
