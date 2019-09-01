package com.epam.news.service;

import java.util.List;

public interface NewsService<T> {
    List<T> getAll();

    T get(int id);

    int add(T news);

    void update(T news);

    void delete(int id);
}
