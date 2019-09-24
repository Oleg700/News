package com.epam.news.service.comment;

import com.epam.news.dao.comment.CommentDao;
import com.epam.news.model.news.Comment;
import com.epam.news.model.user.User;
import com.epam.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class CommentServiceImpl implements CommentService {


    private CommentDao commentDao;


    private UserService userService;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public Comment add(Comment comment, String username) {
        User user = userService.getByName(username);
        comment.setUser(user);
        return commentDao.add(comment);
    }




}
