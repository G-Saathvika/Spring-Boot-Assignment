package com.springboot.demo.studentcourse.repository;

import com.springboot.demo.studentcourse.enity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {

}
