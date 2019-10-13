package com.epam.news.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleForbiddenException(
            Exception ex, WebRequest request, Locale locale) {

        String error = messageSource.getMessage("authentication.exception",
                new Object[]{}, locale);
        return new ResponseEntity<Object>( error
               , new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<Object> handleUnauthorizedException(
            Exception ex, WebRequest request, Locale locale) {
        String error = messageSource.getMessage("authorization.exception",
                new Object[]{}, locale);
        return new ResponseEntity<Object>( error,
                new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
