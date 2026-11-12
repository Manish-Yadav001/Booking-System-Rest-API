package com.demo.BookingSystem.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(ResourceNotFoundException exception) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("Time Stamp", LocalDateTime.now());
        errorResponse.put("Status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("Error", "NOT FOUND");
        errorResponse.put("Message", exception.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

     public static class ResourceNotFoundException extends RuntimeException {

        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @ExceptionHandler (DuplicateResourceException.class)
    public ResponseEntity <Map<String,Object>> handleDuplicateResourceException(DuplicateResourceException exception){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("Time Stamp ", LocalDateTime.now());
        errorResponse.put("Status ", HttpStatus.CONFLICT.value());
        errorResponse.put("Error ","ALREADY EXIST");
        errorResponse.put("Message ", exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    public static class DuplicateResourceException extends RuntimeException{
        public DuplicateResourceException(String message){
            super(message);
        }
    }

}
