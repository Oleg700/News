package com.epam.news.dao.comment;

import com.epam.news.model.news.Comment;

import java.util.Collection;

/**
 * Dao layer for database communication with entity {@link Comment}.
 *
 * @author Oleg Aliyev
 */
public interface CommentDao {

    /**
     * @param comment to save
     * @return saved comment
     */
    Comment add(Comment comment);

    /**
     * Returns comment by news id.
     *
     * @param id   news
     * @param page current page of comments on frontend side
     * @return comment by news id
     */
    Collection<Comment> getCommentsByNewsId(long id, int page);


}
