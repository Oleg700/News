package com.epam.news.controller.comment;

import com.epam.news.model.news.Comment;
import com.epam.news.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RepositoryRestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_COMMENT')")
    @PostMapping(value = "/news/{newsId}/comments")
    public ResponseEntity<Comment>
    addComment(@RequestBody final @Valid Comment comment) {
        String username = getUsernameFromSession();
        Comment savedComment = commentService.add(comment, username);
        return ResponseEntity.ok().body(savedComment);
    }

    /**
     * Retrieve authorize username from session.
     *
     * @return username
     */
    private String getUsernameFromSession() {
        UserDetails user = (UserDetails)
                SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();
        if (user != null) {
            return user.getUsername();
        } else {
            return null;
        }
    }
}
