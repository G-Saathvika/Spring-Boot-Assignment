package com.springboot.demo.studentcourse.repository;

import com.springboot.demo.studentcourse.enity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student,Integer> {
}
