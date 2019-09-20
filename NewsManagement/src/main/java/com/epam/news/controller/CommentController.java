package com.epam.news.controller;

import com.epam.news.model.news.Comment;
import com.epam.news.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@CrossOrigin("*")
@RestController
@EnableWebMvc
@RequestMapping(value = "/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_COMMENT')")
    @PostMapping(value = "/news/{newsId}/comments")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        String username = getUsernameFromSession();
        Comment savedComment = commentService.add(comment, username);
        return ResponseEntity.ok().body(savedComment);
    }

    private String getUsernameFromSession(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return (user != null) ? user.getUsername() : null;
    }

}
