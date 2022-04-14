package com.springboot.demo.studentcourse.controller;

import com.springboot.demo.studentcourse.dto.StudentDTO;
import com.springboot.demo.studentcourse.enity.Course;
import com.springboot.demo.studentcourse.enity.Student;
import com.springboot.demo.studentcourse.service.CourseService;
import com.springboot.demo.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    private static final String STUDENT = "student";
    private static final String COURSES = "courses";
    private static final String STUDENT_LIST= "redirect:/students/list";

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String findAll(Model theModel){

        List<Student> students = studentService.findAll();

        List<StudentDTO> studentDTOS = students.stream().map(student -> modelMapper.map(student,StudentDTO.class)).collect(Collectors.toList());


        if(studentDTOS == null){
            return "nostudent-list";
        }
        else {
            theModel.addAttribute(STUDENT, studentDTOS);
            return "students/student-list";
        }
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        theModel.addAttribute(STUDENT,new Student());

        return "students/student-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("student") Student student,BindingResult theBindingResult,Model theModel){

        if(theBindingResult.hasErrors()){

            List<Course> courses = courseService.findAll();
            theModel.addAttribute(COURSES,courses);

            System.out.println("binding result:"+theBindingResult);

            return "students/student-form";
        }
        else {
            studentService.save(student);

            return STUDENT_LIST;
        }
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") int id, Model theModel){

        Student theStudent = studentService.findById(id);

        theModel.addAttribute(STUDENT,theStudent);

        List<Course> courses = courseService.findAll();

        theModel.addAttribute(COURSES,courses);

        return "students/student-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("studentId") int id){

        studentService.deleteById(id);

        return STUDENT_LIST;
    }

    @GetMapping("/showFormToEnroll")
    public String showFormToEnroll(Model theModel){

        theModel.addAttribute(STUDENT,new Student());

        List<Course> courses = courseService.findAll();

        theModel.addAttribute(COURSES,courses);

        return "students/enroll";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,BindingResult theBindingResult,Model theModel){

        if(theBindingResult.hasErrors()){

            List<Course> courses = courseService.findAll();
            theModel.addAttribute(COURSES,courses);

            return "students/enroll";
        }
        else {
            studentService.save(student);

            return STUDENT_LIST;
        }
    }

    private StudentDTO convertToDTO(Student student){
        StudentDTO studentDTO = modelMapper.map(student,StudentDTO.class);
        studentDTO.setId(student.getId());
        return studentDTO;
    }

    private Student convertToEntity(StudentDTO studentDTO){
        Student student = modelMapper.map(studentDTO,Student.class);

        student.setId(studentDTO.getId());

        if(studentDTO.getId()>0){
            Student oldStudent = studentService.findById(studentDTO.getId());
            student.setFirstName(oldStudent.getFirstName());
            student.setLastName(oldStudent.getLastName());
            student.setEmail(oldStudent.getEmail());
            student.setCourses(oldStudent.getCourses());
        }

        return student;
    }

}
