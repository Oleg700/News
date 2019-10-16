package com.epam.news.repository;

import com.epam.news.model.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface NewsRepository extends JpaRepository<News, Long> {

    @Override
    List<News> findAll();

    @Override
    News save(News news);
}
