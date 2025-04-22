package com.macademy.recordmgmt.services;

import com.macademy.recordmgmt.models.Student;
import com.macademy.recordmgmt.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    // @Value("${student.upload.dir}")
    // private String uploadDir;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentService() {
    }


    //CRUD
    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findByFirstNameContainingIgnoreCase(String search) {

        return studentRepository.findByFirstNameContainingIgnoreCase(search);
    }


    public void saveStudent(Student student) {
        studentRepository.save(student);
    }


    public Page<Student> listAllStudentsToPage(PageRequest pageRequest) {


        return studentRepository.findAll(pageRequest);
    }

    public Student getStudentByStudentId(@Param("studentid") int studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    //CRUD
    public Page<Student> listAllStudents(PageRequest pageRequest) {
        return studentRepository.findAll(pageRequest);
    }


    // TODO explored
    public void updateStudent(Integer studentId, Student student) {
        studentRepository.save(student);
    }

   /* public List<Student> findStudentByStudentId(Integer studentId) {
        return studentRepository.findByStudentId(studentId);
    }*/

    public void deleteStudent(Integer studentId) {
        //studentRepository.delete(findByStudentId(studentId));

        // we need to delete using where clause

    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student findByStudentId(Integer studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public int numberOfStudentsInSchool() {
        int size = studentRepository.findAll().size();
        return size;
    }

    public Map<Integer, Integer> totalStudentsByYearAndGrade() {
        final List<Student> all = studentRepository.findAll();
        Map<Integer, Integer> map = new HashMap<>();
        return map;

    }

    public Map<Integer, Integer> topNStudentsCurrentYearByGrade(Integer numberofstudents) {
        Map<Integer, Integer> map = new HashMap<>();
        return map;
    }

    public Map<Integer, Integer> topNStudentsCurrentYearByGradeByGender(Integer numberofstudents, String gender) {
        Map<Integer, Integer> map = new HashMap<>();
        return map;
    }

    public Student topNAllTimeScorerBySubject(String Subject) {
        return new Student();
    }

    public Page<Student> searchStudentByStudentId(String firstname) {
        String firstnameToUpper = firstname.toUpperCase();
        Pageable p = PageRequest.of(0, 5);
        List<Student> finalList = new ArrayList<>();
        List<Student> list = studentRepository.findAll();
        if (firstname != "")
            finalList = list.stream().filter(pp -> pp.getFirstName().toUpperCase().contains(firstnameToUpper)).collect(Collectors.toList());
        else
            finalList = list;
        final int start = (int) p.getOffset();
        final int end = Math.min((start + p.getPageSize()), finalList.size());
        final Page<Student> page = new PageImpl<>(finalList.subList(start, end), p, finalList.size());
        return page;

    }

}

