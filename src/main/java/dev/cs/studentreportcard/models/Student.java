package dev.cs.studentreportcard.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // make it JPA entity
@Table(name = "Students") // change to any name in database from here
@FieldDefaults(level = AccessLevel.PRIVATE) //make all fields access specifier private
@SequenceGenerator(name = "studentNumber_Seq", initialValue = 1000, allocationSize = 1)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentNumber_Seq")
    @Setter(AccessLevel.PRIVATE)
    Integer studentId;
    @Column(length = 50)
    String firstName;
    @Column(nullable = false, length = 50)
    String middleName;
    @Column(nullable = false, length = 50)
    String lastName;
    @Column(nullable = false)
    LocalDate dateOfBirth;
    @Column(nullable = false, length = 1)
    String gender;
    @Column(nullable = false)
    LocalDate registrationDate;
    @Lob
    @Column(name = "Photo", columnDefinition = "LONGBLOB")
    byte[] photo;
    @Column(nullable = false, length = 50)
    String kifleKetema;
    @Column(columnDefinition = "varchar(50) default NULL")
    String kebele;
    @Column(nullable = false, length = 50)
    String houseNumber;
    @Column(nullable = false, length = 50)
    String phone;
    @Column(length = 150)
    String comment;
    @Column(nullable = false)
    boolean isActive;
    // the mappedBy = student ; this must match the Student student declaration in StudentRecord class
    // @OneToMany(mappedBy = "student" , cascade = CascadeType.ALL)
    // List<StudentRecord> records = new ArrayList<>();

}
