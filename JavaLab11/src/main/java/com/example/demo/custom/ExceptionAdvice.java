package com.example.demo.custom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionAdvice {
//    @ExceptionHandler(value = CustomException.class)
//    public ResponseEntity<CustomError> handleGenericException(CustomException e){
//        CustomError error = new CustomError(e.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<String> exception(CustomException e) {
        return new ResponseEntity<>("Oh boy.. That's it... " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}