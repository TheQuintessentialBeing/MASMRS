package dev.cs.studentreportcard.DTO;

import dev.cs.studentreportcard.models.StudentRecord;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;
import java.util.List;
@Data
//@Entity
//@Immutable  // Hibernate annotation to prevent updates on the view we only create it
//@Table(name = "vw_student_records_header2")  // Map the entity to the view
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRecordHeader {
    @Id
    @Column(name="student_id")
    private Integer studentId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="middle_name")
    private String middleName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="date_of_birth")
    private Date dateOfBirth;
    @Column(name="gender")
    private Character gender;
    @Column(name="academic_year")
    private String academicYear;
    @Column(name="grade")
    private Integer grade;
    @Column(name="section")
    private String section;
    @Column(name="NumberOfSubjects")
    private Integer numberOfSubjects;

    // quarter 1,2 and semester 1
    private double quarterOneSum;
    private Integer quarterOneRank;

    private double quarterTwoSum;
    private Integer quarterTwoRank;

    private Integer semesterOneRank;

    // quarter 3,4 and semester 2
    private double quarterThreeSum;
    private Integer quarterThreeRank;

    private double quarterFourSum;
    private Integer quarterFourRank;

    private Integer semesterTwoRank;

    // rank from all section
    private Integer allSectionRank;
    private Integer totalNumberOfStudents;

    // percentile
    private Integer allSectionPercentile;

    private byte[] photo;
    private boolean isActive;
    private Double semester1Sum;

    //@OneToMany(mappedBy = "studentRecordHeader", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    // @OneToMany(mappedBy = "studentRecordHeader", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<StudentRecord> detailStudentRows; // List of subjects for each student

}

