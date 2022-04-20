package com.springboot.demo.studentcourse.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

}
