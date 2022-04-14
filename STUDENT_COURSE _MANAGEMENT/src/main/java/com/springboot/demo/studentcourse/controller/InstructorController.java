package com.springboot.demo.studentcourse.controller;

import com.springboot.demo.studentcourse.enity.Course;
import com.springboot.demo.studentcourse.enity.Instructor;
import com.springboot.demo.studentcourse.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService theInstructorService) {
        instructorService = theInstructorService;
    }

    @GetMapping("/list")
    public String listInstructors(Model theModel) {

        List<Instructor> instructors = instructorService.findAll();

        theModel.addAttribute("instructor", instructors);

        return "instructors/instructor-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        theModel.addAttribute("instructor", new Instructor());

        return "instructors/instructor-form";
    }

    @PostMapping("/save")
    public String saveInstructor(Instructor theInstructor)
    {

        instructorService.save(theInstructor);

        return "redirect:/instructors/list";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("instructorId") int theId, Model theModel){

        Instructor theInstructor = instructorService.findById(theId);

        theModel.addAttribute("instructor",theInstructor);

        return "instructors/instructor-form";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("instructorId") int theId){

        instructorService.deleteById(theId);

        return "redirect:/instructors/list";

    }


}