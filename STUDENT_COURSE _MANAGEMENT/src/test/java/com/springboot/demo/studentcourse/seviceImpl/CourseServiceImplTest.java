package com.springboot.demo.studentcourse.seviceImpl;


import com.springboot.demo.studentcourse.enity.Course;
import com.springboot.demo.studentcourse.repository.CourseRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void findAll() {
        List<Course> courses = new ArrayList<>();

        Course course1 = new Course("ML");
        Course course2 = new Course("DL");

        courses.add(course1);
        courses.add(course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> courseList = courseService.findAll();

        Java6Assertions.assertThat(2).isEqualTo(courseList.size());
        verify(courseRepository,times(1)).findAll();
    }

    @Test
    void findById() {

        when(courseRepository.findById(2)).thenReturn(Optional.of(new Course(2, "C")));

        Course course = courseService.findById(2);

        Assertions.assertThat(course.getTitle()).isEqualTo("C");
        verify(courseRepository,times(1)).findById(2);
    }

    @Test
    void save() {
        Course course = new Course("DL");
        courseService.save(course);
        verify(courseRepository).save(course);
    }

    @Test
    void deleteById(){
        courseService.deleteById(3);
        verify(courseRepository).deleteById(3);
    }
}