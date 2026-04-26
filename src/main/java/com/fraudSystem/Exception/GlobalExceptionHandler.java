package com.fraudSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException exception){

        Map<String,Object> error = new HashMap<>();

        error.put("TimeStamp", LocalDateTime.now());
        error.put("message",exception.getMessage());
        error.put("status",404);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception e){
        Map<String,Object> error = new HashMap<>();

        error.put("TimeStamp",LocalDateTime.now());
        error.put("message",e.getMessage());
        error.put("Status",404);

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
