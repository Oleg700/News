package com.epam.news.dao.comment;

import com.epam.news.model.news.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Implemetation of interface {@link CommentDao}.
 *
 * @author Oleg Aliyev
 */
public class CommentDaoImpl implements CommentDao {

    /**
     * number of last comment to retrieve from database.
     */
    private static final int NUMBER_COMMENTS = 2;

    /**
     * entity manager is used for communication with database.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment add(final Comment comment) {
        return entityManager.merge(comment);
    }

    @Override
    public Collection<Comment> getCommentsByNewsId(final long id,
                                                   final int page) {
        return (Collection<Comment>) entityManager
                .createNamedQuery("getCommentsByNewsId", Comment.class)
                .setMaxResults(NUMBER_COMMENTS)
                .setFirstResult(page)
                .setParameter("id", id)
                .getResultList();
    }


}
