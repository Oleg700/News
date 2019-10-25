package com.epam.news.repository;

import com.epam.news.model.news.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository
        extends PagingAndSortingRepository<Comment, Long> {

    @Query("SELECT c FROM Comments c where news_id = :#{#id}")
    Page<Comment> findById(@Param("id") long id, Pageable pageable);

}
