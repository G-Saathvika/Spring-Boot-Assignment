package com.springboot.demo.studentcourse.repository;

import com.springboot.demo.studentcourse.enity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {


    @Query("Select s.firstName,s.lastName,s.email,c.title \n" +
            "from Student s \n" +
            "INNER JOIN Course  c ON s.courses=c.students")
    List<Student> findCourseByStudent();
}
