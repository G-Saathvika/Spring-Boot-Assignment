package com.springboot.demo.studentcourse.controller;

import com.springboot.demo.studentcourse.enity.Student;
import com.springboot.demo.studentcourse.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentControllerTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void saveStudent() {
        Student student = new Student("G","Saathvika","saathvika@gmail.com");
        studentRepository.save(student);
        Assertions.assertThat(student.getId()).isGreaterThan(0);
    }

    @Test
    void getStudentById(){
        Student student = studentRepository.findById(3).get();
        Assertions.assertThat(student.getId()).isEqualTo(3);
    }

    @Test
    void findAll() {
        List<Student> students = studentRepository.findAll();
        Assertions.assertThat(students.size()).isGreaterThan(0);
    }

    @Test
    void updateStudent() {
        Student student = studentRepository.findById(2).get();
        student.setEmail("r.jane@gmail.com");

        Student studentUpdated = studentRepository.save(student);
        Assertions.assertThat(studentUpdated.getEmail()).isEqualTo("r.jane@gmail.com");
    }

    @Test
    void deleteStudent() {
        Student student = studentRepository.findById(1).get();
        studentRepository.delete(student);

        Student student1 = null;

        Optional<Student> optionalStudent = studentRepository.findById(1);

        if(optionalStudent.isPresent()){
            student1 = optionalStudent.get();
        }
        Assertions.assertThat(student1).isNull();
    }
}