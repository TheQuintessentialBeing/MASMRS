package com.macademy.recordmgmt.controllers;

import com.macademy.recordmgmt.models.Student;
import com.macademy.recordmgmt.services.CSVDataLoadingService;
import com.macademy.recordmgmt.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private CSVDataLoadingService csvDataLoadingService;
    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService, CSVDataLoadingService csvDataLoadingService) {
        this.studentService        = studentService;
        this.csvDataLoadingService = csvDataLoadingService;
    }

    public StudentController() {
    }

    //  TODO  CRUD - Create
    @PostMapping("/add")
    public String saveStudent(@ModelAttribute("Student") Student Student, BindingResult result, Model model) {
        //TODO exception handling if Student already exists
        studentService.saveStudent(Student);
        model.addAttribute("Student", new Student());
        // model.addAttribute("Studentlines", Studentcodes);
        return "redirect:/Student/admin";
    }

    @GetMapping("/delete/{Studentcode}")
    public String deleteStudent(@PathVariable Integer Studentcode) {
        studentService.deleteStudent(Studentcode);
        return "redirect:/Student/admin";
    }

    @GetMapping("/edit/{Studentcode}")
    public ModelAndView editStudent(@PathVariable("StudentId") Integer Studentcode) {
        ModelAndView editview = new ModelAndView("Studentadd");
        Set<String> Studentcodes = new HashSet<>();
        //for (StudentLine pl : StudentLineService.findAllStudentLine()) {
        //    Studentcodes.add(pl.getStudentLine());
        // }
        editview.addObject("Studentlines", Studentcodes);
        Student Student = studentService.findStudentByStudentId(Studentcode);
        editview.addObject("Student", Student);
        return editview;
    }

    //  TODO CRUD - Read
    @GetMapping("/listptm")
    public ResponseEntity<List<Student>> showAllStudents() {
        List<Student> students = new ArrayList<>();
        students = studentService.listAllStudents();
        System.out.println("Testing Student conteroller list if it returns anything");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/list")
    public String showAllStudents(HttpServletRequest request, Model model) {
        int page = 0;
        int size = 5;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("students", studentService.listAllStudentsToPage(PageRequest.of(page, size)));
        return "students";
    }


    // TODO CRUD - update
//  TODO  CRUD - Delete
//  TODO  Advanced - Search
//  TODO Student Stats
//  TODO Top Student
//  TODO  Forever Scorer by Subject this or across the years ( 1 or more)

    /*This method shows all students POSTMAN*/
    // TODO DONE
/*    @GetMapping("/list")
    public ResponseEntity<List<Student>> showAllStudentsP() {
        List<Student> students = new ArrayList<>();
        students = studentService.listAllStudents();
        System.out.println("Testing Student conteroller list if it returns anything");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }*/


    // top student from every class
    /*This method shows all students POSTMAN*/
// Working post man
    @GetMapping("/loadstudents")
    public ResponseEntity<String> loadStudentCsv() throws IOException {
        csvDataLoadingService.loadCsvStudentDataFile();
        System.out.println("Controller called for Student Record data....");
        return ResponseEntity.ok("Student data inserted successfully!");
    }

    // Working post man
    @GetMapping("/loadstudentrecords")
    public ResponseEntity<String> loadStudentRecordCsv() throws IOException {
        csvDataLoadingService.loadStudentRecordDataFile();
        return ResponseEntity.ok("Student record data inserted successfully!");
    }
}