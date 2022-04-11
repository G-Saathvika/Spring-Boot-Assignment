package com.springboot.demo.studentcourse.service;

import com.springboot.demo.studentcourse.enity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(int id);

    Student save(Student student);

    void deleteById(int id);

    List<Student> findCourseByStudent();

}
