package com.springboot.demo.studentcourse.seviceImpl;

import com.springboot.demo.studentcourse.enity.Student;
import com.springboot.demo.studentcourse.repository.StudentRepository;
import com.springboot.demo.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Page<Student> getPaginatedStudents(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student findById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

}
