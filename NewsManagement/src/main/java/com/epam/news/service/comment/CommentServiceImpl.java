package com.epam.news.service.comment;

import com.epam.news.dao.comment.CommentDao;
import com.epam.news.model.Comment;
import com.epam.news.model.CommentRequest;
import com.epam.news.model.User;
import com.epam.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class CommentServiceImpl implements CommentService {


    private CommentDao commentDao;

    @Autowired
    private UserService userService;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public Comment add(CommentRequest commentRequest) {
        String username = commentRequest.getUsername();
        User user = userService.getByName(username);
        Comment comment = commentRequest.getComment();
        comment.setUser(user);
        return commentDao.add(comment);
    }
}
