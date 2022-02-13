package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.exeptions.StudentNotFound;
import ru.geekbrains.persist.Student;
import ru.geekbrains.persist.StudentRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(Student.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        logger.info("Student list page requested");
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }

    @GetMapping("/new")
    public String newStudentForm(Model model) {
        logger.info("New student page requested");
        model.addAttribute("student", new Student());
        return "student_form";
    }

    @GetMapping("/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentRepository.getStudentById(id)
                .orElseThrow(() -> new StudentNotFound("Student (id: " + id + ") not found")));
        logger.info("Request student id: " + id);
        return "student_info";
    }

    @GetMapping("/remove/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
        studentRepository.remove(id);
        return "redirect:/student";
    }

    @PostMapping
    public String update(@Valid Student student, BindingResult result) {
        logger.info("Saving student");
        if (result.hasErrors()) {
            logger.info("Bad input");
            return "student_info";
        }
        studentRepository.save(student);
        return "redirect:/student";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView studentNotFoundExceptionHandler(StudentNotFound ex) {
        ModelAndView modelAndView = new ModelAndView("student_not_found_form");
        modelAndView.addObject("message", ex.getMessage());
        logger.warn("Exception: " + ex.getMessage());
        return modelAndView;
    }
}
