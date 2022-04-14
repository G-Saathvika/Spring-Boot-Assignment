package com.springboot.demo.studentcourse.controller;

import com.springboot.demo.studentcourse.enity.Student;
import com.springboot.demo.studentcourse.repository.StudentRepository;
import com.springboot.demo.studentcourse.service.StudentService;
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
class StudentControllerTest{


    private StudentRepository studentRepository;


    private StudentService studentService;

    @Test
    void findAll() {
        List<Student> students = new ArrayList<>();

        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        Student student1 = new Student("G","Saathvika","saa@gmail.com");
        Student student2 = new Student("K","Sara","sara@gmail.com");

        students.add(student1);
        students.add(student2);

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> studentList = studentService.findAll();

        assertThat(2 == studentList.size());

    }

    @Test
    void getStudentById(){
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        when(studentRepository.findById(2)).thenReturn(Optional.of(new Student(2, "Rachel", "Jane", "rachel@gmail.com")));

        Student student = studentService.findById(2);

        assertThat(student.getFirstName().equals("Rachel"));
        assertThat(student.getLastName().equals("Jane"));
        assertThat(student.getEmail().equals("rachel@gmail.com"));

    }

    @Test
    void updateStudent(){
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        Student student = new Student(15,"a","b","xyz@gmail.com");

        when(studentRepository.save(student)).thenReturn(student);

        Student expectedStudent = studentService.save(student);

        assertThat(expectedStudent).isNotNull();
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void deleteStudent(){
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        int id = 3;

        studentService.deleteById(id);

        verify(studentRepository,times(1)).deleteById(id);
    }
}
