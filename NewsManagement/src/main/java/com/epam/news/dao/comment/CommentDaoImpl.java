package com.epam.news.dao.comment;

import com.epam.news.model.news.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment add(Comment comment) {
        return entityManager.merge(comment);
    }

    @Override
    public Collection<Comment> getCommentsByNewsId(long id, int page) {
        return (Collection<Comment>) entityManager.createQuery("select c from Comments c where c.news_id = :id")
                .setMaxResults(page)
                .setFirstResult(page)
                .setParameter("id", id)
                .getSingleResult();
    }
}
