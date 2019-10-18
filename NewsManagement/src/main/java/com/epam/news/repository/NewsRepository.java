package com.epam.news.repository;

import com.epam.news.model.news.News;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Override
    @EntityGraph(attributePaths = {"comments"})
    News getOne(Long id);

    Long deleteById(final long id);

    @Query(value = "SELECT n.title, n.brief FROM News n")
    List<News> findAllNews();
}
