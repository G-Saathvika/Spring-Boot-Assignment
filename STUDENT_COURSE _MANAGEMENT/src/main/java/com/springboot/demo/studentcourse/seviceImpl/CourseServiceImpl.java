package com.springboot.demo.studentcourse.seviceImpl;

import com.springboot.demo.studentcourse.enity.Course;
import com.springboot.demo.studentcourse.repository.CourseRepository;
import com.springboot.demo.studentcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository theCourseRepository){
        courseRepository = theCourseRepository;
    }


    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }


}