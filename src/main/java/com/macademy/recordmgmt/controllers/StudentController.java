package com.macademy.recordmgmt.controllers;

import com.macademy.recordmgmt.models.Student;
import com.macademy.recordmgmt.services.StudentService;
import com.macademy.recordmgmt.services.TestDataCSVLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private final TestDataCSVLoadService testDataCSVLoadService;
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService, TestDataCSVLoadService testDataCSVLoadService) {
        this.studentService         = studentService;
        this.testDataCSVLoadService = testDataCSVLoadService;
    }

    //  TODO CRUD - Read works
    @GetMapping("/listpm")
    public ResponseEntity<List<Student>> showAllStudentspm() {
        List<Student> students = studentService.listAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/list")
    public String showAllStudents(Model model) {
        List<Student> students = studentService.listAllStudents();
        model.addAttribute("student", students);
        return "studentlist";
    }

    @GetMapping("/find/{StudentId}")
    public ResponseEntity<Student> findStudent(@PathVariable Integer StudentId) {
        Student student = studentService.findByStudentId(StudentId);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    //  TODO  CRUD - Create
    @PostMapping("/add")
    public Student createStudent(@RequestBody Student Student) {
        return Student;
    }


    @PutMapping("/update/{StudentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer StudentId, @RequestBody Student updatedStudent) {

        for (Student s : studentService.getAllStudents()) {
            if (s.getStudentId() == StudentId) {
                s.setComment(updatedStudent.getComment());
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/edit/{Studentcode}")
    public ModelAndView updateStudent(@PathVariable("StudentId") Integer Studentcode) {
        ModelAndView editview = new ModelAndView("Studentadd");
        Set<String> Studentcodes = new HashSet<>();
        //for (StudentLine pl : StudentLineService.findAllStudentLine()) {
        //    Studentcodes.add(pl.getStudentLine());
        // }/**/
        editview.addObject("Studentlines", Studentcodes);
        // Student Student = studentService.findStudentByStudentId(Studentcode);
        //editview.addObject("Student", Student);
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

   /* @GetMapping("/list")
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
    }*/

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

    /*This method shows all students POSTMAN*/
// Working post man
    @GetMapping("/loadstudents")
    public ResponseEntity<String> loadStudentCsv() throws IOException {
        testDataCSVLoadService.loadCsvStudentDataFile();
        System.out.println("Controller called for Student Record data....");
        return ResponseEntity.ok("Student data inserted successfully!");
    }

    // Working post man
    @GetMapping("/loadstudentrecords")
    public ResponseEntity<String> loadStudentRecordCsv() throws IOException {
        testDataCSVLoadService.loadStudentRecordDataFile();
        return ResponseEntity.ok("Student record data inserted successfully!");
    }
}