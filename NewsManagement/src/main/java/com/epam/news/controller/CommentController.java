/*
package com.epam.news.controller;

import com.epam.news.model.news.Comment;
import com.epam.news.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

*/
/**
 * REST API controller for processing queries with entity {@link Comment}.
 *
 * @author Oleg Aliyev
 *//*

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api")
public class CommentController {

    */
/**
     * service is used to interact with database.
     *//*

    @Autowired
    private CommentService commentService;

    */
/**
     * Save comment to database.
     *
     * @param comment to save
     * @return comment
     *//*

    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_COMMENT')")
    @PostMapping(value = "/news/{newsId}/comments")
    public ResponseEntity<Comment>
    addComment(@RequestBody final @Valid Comment comment) {
        String username = getUsernameFromSession();
        Comment savedComment = commentService.add(comment, username);
        return ResponseEntity.ok().body(savedComment);
    }

    */
/**
     * Retrieve authorize username from session.
     *
     * @return username
     *//*

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
*/
