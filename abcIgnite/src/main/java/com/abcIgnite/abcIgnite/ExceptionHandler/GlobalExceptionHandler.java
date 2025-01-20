package com.abcIgnite.abcIgnite.ExceptionHandler;

import com.abcIgnite.abcIgnite.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> illegalArgumentException(IllegalArgumentException ex) {
        Response customResponse = new Response("Failure", ex.getMessage(),null );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customResponse);
    }
}
