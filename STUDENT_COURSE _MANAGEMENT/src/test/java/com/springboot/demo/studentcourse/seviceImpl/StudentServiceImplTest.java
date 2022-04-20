package com.springboot.demo.studentcourse.seviceImpl;

import com.springboot.demo.studentcourse.enity.Student;
import com.springboot.demo.studentcourse.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void findAll() {
        List<Student> students = new ArrayList<>();

        Student student1 = new Student("G","Saathvika","saa@gmail.com");
        Student student2 = new Student("K","Sara","sara@gmail.com");

        students.add(student1);
        students.add(student2);

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> studentList = studentService.findAll();

        assertThat(2).isEqualTo(studentList.size());
        verify(studentRepository,times(1)).findAll();
    }

    @Test
    void findById() {

        when(studentRepository.findById(2)).thenReturn(Optional.of(new Student(2, "Rachel", "Jane", "rachel@gmail.com")));

        Student student = studentService.findById(2);

        Assertions.assertThat(student.getFirstName()).isEqualTo("Rachel");
        Assertions.assertThat(student.getLastName()).isEqualTo("Jane");
        Assertions.assertThat(student.getEmail()).isEqualTo("rachel@gmail.com");
        verify(studentRepository,times(1)).findById(2);


    }

    @Test
    void save() {
        Student student = new Student("G","saathvika","saatg@gmail.com");
        studentService.save(student);
        verify(studentRepository).save(student);
    }

    @Test
    void update(){
        Student student = new Student();
        student.setFirstName("G");
        student.setLastName("saathvika");
        student.setEmail("saathvika.g@gmail.com");

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        student.setFirstName("S");
        student.setEmail("saatg@gmail.com");

        Student updated = studentService.update(student);

        assertThat(updated.getFirstName()).isEqualTo("S");
        assertThat(updated.getEmail()).isEqualTo("saatg@gmail.com");

        verify(studentRepository,times(1)).save(updated);

    }

    @Test
    void deleteById(){
        studentService.deleteById(3);
        verify(studentRepository).deleteById(3);
    }

    @Test
    void getPaginatedStudents(){
        Pageable pageable = PageRequest.of(1,4);

        Student student = new Student("G","saathvika","saatg@gmail.com");
        Page<Student> studentPage = new PageImpl<>(Collections.singletonList(student));
        when(studentRepository.findAll(pageable)).thenReturn(studentPage);
        Page<Student> students = studentRepository.findAll(pageable);
        assertThat(students.getTotalElements()).isEqualTo(1);
    }
}