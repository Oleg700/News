package com.epam.news.service.news;

import com.epam.news.model.news.News;

import java.util.List;

public interface NewsService {

    List<News> getAll();

    News get(long id);

    News add(News news);

    News update(News news);

    void delete(long id);
}
