package com.springboot.demo.studentcourse.dto;

import com.springboot.demo.studentcourse.enity.Course;
import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Course> courses;
}
