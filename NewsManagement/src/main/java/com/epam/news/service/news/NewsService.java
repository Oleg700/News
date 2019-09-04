package com.epam.news.service.news;

import com.epam.news.model.Authority;
import com.epam.news.model.News;
import com.epam.news.model.User;

import java.util.List;

public interface NewsService{

    List<News> getAll();

    List<Authority> getAllAuthorities();
    Authority addAuthority(Authority authority);

    News get(long id);

    News add(News news);

    News update(News news);

    void delete(long id);
}
