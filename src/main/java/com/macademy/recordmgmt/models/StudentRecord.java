package com.macademy.recordmgmt.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // make it JPA entity
@Table(name = "StudentRecords") // make table name "Result" in db
@FieldDefaults(level = AccessLevel.PRIVATE) //make all fields access specifier private
@SequenceGenerator(name = "recordsNumber_Seq",        // Unique sequence name
        sequenceName = "records_seq",      // Database sequence name
        initialValue = 1001,               // Start value
        allocationSize = 1                 // Increment by 1
)
/*TODO Tobe aware We need to run this on the db as jpa don't create sequence automatically
CREATE TABLE records_seq (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
) AUTO_INCREMENT = 1001;
*/
@Getter
@Setter
@ToString
public class StudentRecord { /*TODO entities shall be singular but db names shall be plural*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Integer recordId;
    @Column(name = "student_id")
    Integer studentId;
    @Column(length = 50)
    String subject;
    @Column(nullable = false, length = 50)
    String academicYear;
    @Min(9)
    @Max(12)
    @Column(nullable = false)
    Integer grade;
    @Column(nullable = false, length = 1)
    String section;
    @Column(precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double q1;
    @Column(precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double q2;
    @Column(precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double q3;
    @Column(precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double q4;
    @Column(nullable = false) // TODO this could be many to One or use the table design we drafted.
    Integer updatedBy;
    @Column(nullable = false) // TODO this should nullable=false and should pick today's date while the user enters data on the page/form
    LocalDate updateDate;
    @Column(length = 150)
    String comment;

    // @ManyToOne // StudentRecord(many records) will match (One Student) Remember : Student Id must exist in Student Table before it is used/insert in StudentRecord table
    // @JoinColumn( name= "FK_StudentId", referencedColumnName = "studentId")// in production this should be set , nullable = true)
    // FK_StudentId is name of the foreign key
    // studentId is the primary key of the parent table i,e Student
    // Student student;
    // studnet is here to navigate b/n the tables
}

