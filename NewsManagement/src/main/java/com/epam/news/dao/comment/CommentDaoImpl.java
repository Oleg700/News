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
        return (Collection<Comment>) entityManager.createNamedQuery("getCommentsByNewsId", Comment.class)
                .setMaxResults(page)
                .setParameter("id", id)
                .getResultList();
    }


}
