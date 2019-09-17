package com.epam.news.dao.comment;

import com.epam.news.model.news.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment add(Comment comment) {
        return entityManager.merge(comment);
    }
}
