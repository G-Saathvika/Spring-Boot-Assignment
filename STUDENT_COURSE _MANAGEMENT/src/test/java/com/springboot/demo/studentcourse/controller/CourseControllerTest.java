package com.springboot.demo.studentcourse.controller;

import com.springboot.demo.studentcourse.enity.Course;
import com.springboot.demo.studentcourse.enity.Student;
import com.springboot.demo.studentcourse.repository.CourseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseControllerTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void saveCourse() {
        Course course = new Course("Deep Learning");
        courseRepository.save(course);
        Assertions.assertThat(course.getId()).isGreaterThan(0);
    }

    @Test
    void getCourseById(){
        Course course = courseRepository.findById(5).get();
        Assertions.assertThat(course.getId()).isEqualTo(5);
    }

    @Test
    void listCourses() {
        List<Course> courses = courseRepository.findAll();
        Assertions.assertThat(courses.size()).isGreaterThan(0);
    }

    @Test
    void updateStudent() {
        Course course = courseRepository.findById(7).get();
        course.setTitle("DM");

        Course courseUpdated = courseRepository.save(course);
        Assertions.assertThat(courseUpdated.getTitle()).isEqualTo("DM");
    }

    @Test
    void deleteStudent() {
        Course course = courseRepository.findById(3).get();
        courseRepository.delete(course);

        Course course1 = null;

        Optional<Course> optionalCourse = courseRepository.findById(3);

        if(optionalCourse.isPresent()){
            course1 = optionalCourse.get();
        }
        Assertions.assertThat(course1).isNull();
    }
}