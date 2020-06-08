package com.epam.news.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleForbiddenException(
            final Exception ex, final WebRequest request, final Locale locale) {

        String error = messageSource.getMessage("authentication.exception",
                new Object[]{}, locale);
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType(
                "application", "json", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);
        Map result = new HashMap<String, String>();
        result.put("message", error);
        return new ResponseEntity<Object>(result,
                headers, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleUnauthorizedException(
            final Exception ex, final WebRequest request, final Locale locale) {
        String error = messageSource.getMessage("authorization.exception",
                new Object[]{}, locale);
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType(
                "application", "json", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);
        Map result = new HashMap<String, String>();
        result.put("message", error);
        return new ResponseEntity<Object>(result,
                headers, HttpStatus.UNAUTHORIZED);
    }
}
