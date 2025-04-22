package com.macademy.recordmgmt.repositories;

import com.macademy.recordmgmt.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // student

    @Query(name = "SqlSearchStudentByStudentId")
    Student findByStudentId(@Param("studentId") Integer studentId);

    List<Student> findByFirstNameContainingIgnoreCase(String name);

    // List<Student> findByEmailContainingIgnoreCase(String email);

}
