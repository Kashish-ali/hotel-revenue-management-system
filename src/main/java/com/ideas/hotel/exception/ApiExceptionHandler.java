package com.ideas.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class, org.springframework.dao.EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handle(Exception e) { return Map.of("error", e.getMessage() == null ? "Invalid request" : e.getMessage()); }
}
