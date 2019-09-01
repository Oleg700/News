package com.epam.news.dao;

import java.util.List;

public interface NewsDao<T> {
    List<T> getAll();

    T get(int id);

    int add(T news);

    void update(T news);

    void delete(int id);
}
