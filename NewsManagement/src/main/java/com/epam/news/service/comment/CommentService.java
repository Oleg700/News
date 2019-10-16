package com.epam.news.service.comment;

import com.epam.news.model.news.Comment;


/**
 * Service for wrapping {@link com.epam.news.dao.comment.CommentDao}.
 *
 * <p>
 * Implementation {@link CommentServiceImpl}.
 * <p>
 *
 * @author Oleg Aliyev
 */

public interface CommentService {

    /**
     * @param comment  to save
     * @param username of authorized user
     * @return added comment
     */
    Comment add(Comment comment, String username);


}
