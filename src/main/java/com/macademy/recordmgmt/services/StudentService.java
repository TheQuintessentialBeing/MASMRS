package com.macademy.recordmgmt.services;

import com.macademy.recordmgmt.models.Student;
import com.macademy.recordmgmt.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentService() {
    }

    //CRUD
    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> searchByStudentIdOrEmailOrNameContainingIgnoreCase(String search) {
        return studentRepository.findByStudentIdOrNameContainingIgnoreCase(search);
    }


    public Student saveStudent(Student student) {
        studentRepository.save(student);
        return student;
    }


    public void deleteStudent(Integer studentId) {
      studentRepository.deleteById(studentId);

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
     public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student findByStudentId(Integer studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public Student updateStudent(Integer studentId, Student updatedStudent) {
        Student e = studentRepository.findById(studentId)
               .orElseThrow(() -> new RuntimeException("Student not found"));


        e.setFirstName(updatedStudent.getFirstName());
        e.setMiddleName(updatedStudent.getMiddleName());
        e.setLastName(updatedStudent.getLastName());
        e.setDateOfBirth(updatedStudent.getDateOfBirth());
        e.setGender(updatedStudent.getGender());
        e.setRegistrationDate(updatedStudent.getRegistrationDate());
        e.setPhone(updatedStudent.getPhone());
        e.setKifleKetema(updatedStudent.getKifleKetema());
        e.setKebele(updatedStudent.getKebele());
        e.setHouseNumber(updatedStudent.getHouseNumber());
        e.setComment(updatedStudent.getComment());
        e.setActive(updatedStudent.getIsActive());
        System.out.println("e" + e);
        System.out.println("updated" + updatedStudent);
        return studentRepository.save(e);
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

   /* public Page<Student> searchStudentByStudentId(String firstname) {
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
    }*/

}

