package com.epam.news.dao.news;

import com.epam.news.model.Authority;
import com.epam.news.model.News;
import com.epam.news.model.User;

import java.util.List;

public interface NewsDao {




    List<Authority> getAllAuthorities();

    Authority addAuthority(Authority authority);


    List<News> getAll();

    News get(long id);

    News add(News news);

    News update(News news);

    void delete(long id);
}
