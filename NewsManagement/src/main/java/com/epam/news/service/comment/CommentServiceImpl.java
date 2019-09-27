package com.epam.news.service.comment;

import com.epam.news.dao.comment.CommentDao;
import com.epam.news.model.news.Comment;
import com.epam.news.model.user.User;
import com.epam.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * implementation of interface {@link CommentService}.
 *
 * @author Oleg Aliyev
 */
public class CommentServiceImpl implements CommentService {

    /**
     * commentDao is used for connection with database.
     */
    private CommentDao commentDao;

    /**
     * userService is used to get user by name.
     */
    private UserService userService;

    /**
     * Constructor.
     *
     * @param commentDao  is used for connection with database.
     * @param userService is used to get user by name.
     */
    @Autowired
    public CommentServiceImpl(final CommentDao commentDao,
                              final UserService userService) {
        this.commentDao = commentDao;
        this.userService = userService;
    }


    @Override
    @Transactional
    public Comment add(final Comment comment, final String username) {
        User user = userService.getByName(username);
        comment.setUser(user);
        return commentDao.add(comment);
    }


}
