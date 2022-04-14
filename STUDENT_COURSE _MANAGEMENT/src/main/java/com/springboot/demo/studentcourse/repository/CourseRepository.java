package com.springboot.demo.studentcourse.repository;

import com.springboot.demo.studentcourse.enity.Course;
import org.springframework.data.repository.CrudRepository;


public interface CourseRepository extends CrudRepository<Course,Integer> {

}
