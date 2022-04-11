package com.springboot.demo.studentcourse.service;

import com.springboot.demo.studentcourse.enity.Course;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course findById(int id);

    Course save(Course course);

    void deleteById(int id);
}
