package com.epam.news.repository;

import com.epam.news.model.news.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    List<Comment> findAll();

    @Override
    Comment save(Comment news);
}
