package com.springboot.demo.studentcourse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CourseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CoursesErrorResponse> handleException(Exception exc){

        CoursesErrorResponse error = new CoursesErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),System.currentTimeMillis());

        return new ResponseEntity<CoursesErrorResponse>(error,HttpStatus.BAD_REQUEST);
    }
}
