package com.springboot.demo.studentcourse.service;

import com.springboot.demo.studentcourse.enity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Page<Student> getPaginatedStudents(int pageNumber);

    Student findById(int id);

    Student save(Student student);

    Student update(Student student);

    void deleteById(int id);



}
