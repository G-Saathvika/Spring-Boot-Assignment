package com.springboot.demo.studentcourse.controller;

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

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    private CourseService courseService;

    private static final String STUDENT = "student";
    private static final String COURSE = "courses";
    private static final String STUDENT_LIST= "redirect:/students/list";

    @Autowired
    public StudentController(StudentService theStudentService,CourseService theCourseService){
        studentService = theStudentService;
        courseService = theCourseService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String findAll(Model theModel){

        List<Student> students = studentService.findAll();

        theModel.addAttribute(STUDENT,students);

        return "students/student-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        theModel.addAttribute(STUDENT,new Student());

        return "students/student-form";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student theStudent){

            studentService.save(theStudent);

            return STUDENT_LIST;


    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") int id, Model theModel){

        Student theStudent = studentService.findById(id);

        theModel.addAttribute(STUDENT,theStudent);

        List<Course> courses = courseService.findAll();

        theModel.addAttribute(COURSE,courses);

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

        theModel.addAttribute(COURSE,courses);

        return "students/enroll";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@Valid @ModelAttribute("student") Student student,BindingResult theBindingResult,Model theModel){

        if(theBindingResult.hasErrors()){

            theModel.addAttribute(STUDENT,student);
            List<Course> courses = courseService.findAll();
            theModel.addAttribute(COURSE,courses);

            return "students/enroll";
        }
        else {
            studentService.save(student);

            return STUDENT_LIST;
        }
    }

}
