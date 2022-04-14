package com.springboot.demo.studentcourse.controller;

import com.springboot.demo.studentcourse.enity.Course;
import com.springboot.demo.studentcourse.exception.CourseException;
import com.springboot.demo.studentcourse.service.CourseService;
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
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    private static final String COURSE = "course";
    private static final String COURSE_FORM = "courses/course-form";

    @Autowired
    public CourseController(CourseService theCourseService){
        courseService = theCourseService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String listCourses(Model model)
    {
        List<Course> courses = courseService.findAll();

        model.addAttribute(COURSE, courses);

        return "courses/course-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        theModel.addAttribute(COURSE, new Course());

        return COURSE_FORM;
    }

    @PostMapping("/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course theCourse, BindingResult theBindingResult,Model theModel)
    {
        try {
            if (theBindingResult.hasErrors()) {

                theModel.addAttribute(COURSE, new Course());

                return COURSE_FORM;
            } else {
                courseService.save(theCourse);

                return "redirect:/students/list";
            }
        }
        catch (Exception ex){
            return "error";
        }

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("courseId") int theId, Model theModel){

        Course theCourse = courseService.findById(theId);

        theModel.addAttribute(COURSE,theCourse);

        return COURSE_FORM;

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("courseId") int theId){

        courseService.deleteById(theId);

        return "redirect:/courses/list";

    }

}