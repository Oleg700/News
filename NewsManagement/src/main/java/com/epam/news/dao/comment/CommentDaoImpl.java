package com.epam.news.dao.comment;

import com.epam.news.model.news.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

public class CommentDaoImpl implements CommentDao {

    private final static int NUMBER_COMMENTS = 2;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment add(Comment comment) {
        return entityManager.merge(comment);
    }

    @Override
    public Collection<Comment> getCommentsByNewsId(long id, int page) {
        return (Collection<Comment>) entityManager.createNamedQuery("getCommentsByNewsId", Comment.class)
                .setMaxResults(NUMBER_COMMENTS)
                .setFirstResult(page)
                .setParameter("id", id)
                .getResultList();
    }


}
