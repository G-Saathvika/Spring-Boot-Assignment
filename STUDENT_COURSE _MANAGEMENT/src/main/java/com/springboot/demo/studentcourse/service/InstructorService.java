package com.springboot.demo.studentcourse.service;

import com.springboot.demo.studentcourse.enity.Course;
import com.springboot.demo.studentcourse.enity.Instructor;

import java.util.List;

public interface InstructorService {

    List<Instructor> findAll();

    Instructor findById(int id);

    Instructor save(Instructor instructor);

    void deleteById(int id);

}
