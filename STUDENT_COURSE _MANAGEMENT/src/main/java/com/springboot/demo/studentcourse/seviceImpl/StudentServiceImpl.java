package com.springboot.demo.studentcourse.seviceImpl;

import com.springboot.demo.studentcourse.enity.Student;
import com.springboot.demo.studentcourse.repository.StudentRepository;
import com.springboot.demo.studentcourse.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository theStudentRepository){
        studentRepository = theStudentRepository;

    }

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findCourseByStudent() {
        return studentRepository.findCourseByStudent();
    }

}
