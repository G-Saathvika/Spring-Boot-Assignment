package com.springboot.demo.studentcourse.repository;

import com.springboot.demo.studentcourse.enity.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course,Integer> {

}
