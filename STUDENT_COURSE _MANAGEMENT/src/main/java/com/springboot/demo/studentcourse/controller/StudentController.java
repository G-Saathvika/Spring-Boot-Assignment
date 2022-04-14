package com.springboot.demo.studentcourse.controller;

import com.springboot.demo.studentcourse.dto.StudentDTO;
import com.springboot.demo.studentcourse.enity.Course;
import com.springboot.demo.studentcourse.enity.Student;
import com.springboot.demo.studentcourse.service.CourseService;
import com.springboot.demo.studentcourse.seviceImpl.MappingService;
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

    private MappingService mappingService;

    private StudentService studentService;

    private CourseService courseService;

    private static final String STUDENT = "student";
    private static final String COURSES = "courses";
    private static final String STUDENT_LIST= "redirect:/students/list";

    @Autowired
    public StudentController(StudentService theStudentService,CourseService theCourseService,MappingService theMappingService){
        studentService = theStudentService;
        courseService = theCourseService;
        mappingService = theMappingService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String findAll(Model theModel){

        List<StudentDTO> students = mappingService.getAllStudentCourses();

        if(students.isEmpty()){
            return "nostudent-list";
        }
        else {
            theModel.addAttribute(STUDENT, students);

            //System.out.println(students);

            return "students/student-list";
        }
    }

//    @GetMapping("/list")
//    public String findAll(Model theModel){
//
//        List<Student> students = studentService.findAll();
//
//        theModel.addAttribute(STUDENT,students);
//
//        return "students/student-list";
//    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        theModel.addAttribute(STUDENT,new Student());

        return "students/student-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("student") Student student,BindingResult theBindingResult,Model theModel){

        if(theBindingResult.hasErrors()){

            //theModel.addAttribute(STUDENT,student);
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

            //theModel.addAttribute(STUDENT,student);
            List<Course> courses = courseService.findAll();
            theModel.addAttribute(COURSES,courses);

            //System.out.println("binding result:"+theBindingResult);

            return "students/enroll";
        }
        else {
            studentService.save(student);

            return STUDENT_LIST;
        }
    }

}
