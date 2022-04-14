package com.springboot.demo.studentcourse.seviceImpl;

import com.springboot.demo.studentcourse.dto.StudentDTO;
import com.springboot.demo.studentcourse.enity.Course;
import com.springboot.demo.studentcourse.enity.Student;
import com.springboot.demo.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MappingService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAllStudentCourses(){
        return ((List<Student>) studentRepository.findAll()).stream().map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }

    private StudentDTO convertDataIntoDTO (Student student){

        StudentDTO dto = new StudentDTO();

        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());

        List<Course> courses = student.getCourses();
        dto.setCourses(courses);

        return dto;
    }
}
