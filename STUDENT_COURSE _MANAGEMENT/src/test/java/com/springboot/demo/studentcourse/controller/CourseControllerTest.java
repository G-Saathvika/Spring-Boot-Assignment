package com.springboot.demo.studentcourse.controller;

import com.springboot.demo.studentcourse.enity.Course;
import com.springboot.demo.studentcourse.enity.Student;
import com.springboot.demo.studentcourse.repository.CourseRepository;
import com.springboot.demo.studentcourse.repository.StudentRepository;
import com.springboot.demo.studentcourse.service.CourseService;
import com.springboot.demo.studentcourse.seviceImpl.CourseServiceImpl;
import com.springboot.demo.studentcourse.seviceImpl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
class CourseControllerTest {

    private CourseRepository courseRepository;

    private CourseService courseService;

    @Test
    void findAll() {
        courseRepository = mock(CourseRepository.class);
        courseService = new CourseServiceImpl(courseRepository);

        List<Course> courses = new ArrayList<>();

        courses.add(new Course(100, "Deep Learning"));
        courses.add(new Course(101, "ML"));

        when(courseRepository.findAll()).thenReturn(courses);
        List<Course> courseList = courseService.findAll();

        assertThat(courseList.size()).isEqualTo(2);
    }

    @Test
    void findCourseById(){
        courseRepository = mock(CourseRepository.class);
        courseService = new CourseServiceImpl(courseRepository);

        when(courseRepository.findById(2)).thenReturn(Optional.of(new Course(2, "C")));

        Course course = courseService.findById(2);

        assertThat(course.getTitle().equals("C"));

    }

    @Test
    void updateCourse(){
        courseRepository = mock(CourseRepository.class);
        courseService = new CourseServiceImpl(courseRepository);

        Course course = new Course(100,"ML");

        when(courseRepository.save(course)).thenReturn(course);

        Course expectedCourse = courseService.save(course);

        assertThat(expectedCourse).isNotNull();
        verify(courseRepository).save(any(Course.class));
    }

    @Test
    void deleteStudent(){
        courseRepository = mock(CourseRepository.class);
        courseService = new CourseServiceImpl(courseRepository);

        int id = 3;

        courseService.deleteById(id);

        verify(courseRepository,times(1)).deleteById(id);
    }
}