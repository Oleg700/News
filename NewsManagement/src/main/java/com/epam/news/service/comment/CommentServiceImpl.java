package com.epam.news.service.comment;

import com.epam.news.model.news.Comment;
import com.epam.news.model.user.User;
import com.epam.news.repository.CommentRepository;
import com.epam.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * implementation of interface {@link CommentService}.
 *
 * @author Oleg Aliyev
 */
@Service
public class CommentServiceImpl implements CommentService {

    /**
     * commentDao is used for connection with database.
     */
    private CommentRepository commentRepository;

    /**
     * userService is used to get user by name.
     */
    private UserRepository userRepository;


    @Autowired
    public CommentServiceImpl(final CommentRepository commentRepository,
                              final UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public Comment add(final Comment comment, final String username) {
        User user = userRepository.findDistinctFirstByUsername(username);
        comment.setUser(user);
        return commentRepository.save(comment);

    }


}
