package dev.cs.studentreportcard.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // make it JPA entity
@Table(name = "Records") // make table name "Result" in db
@FieldDefaults(level = AccessLevel.PRIVATE) //make all fields access specifier private
@SequenceGenerator(name = "recordsNumber_Seq", initialValue = 1000, allocationSize = 1)
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recordsNumber_Seq")
    @Setter(AccessLevel.PRIVATE)
    Integer recordsId;
    //FK - Relationship TODO
    Integer studentId;
    @Column(length = 50)
    String subject;
    @Column(nullable = false, length = 50)
    String academicYear;
    @Column(nullable = false, length = 1)
    String grade;
    @Column(nullable = false, length = 2)
    String section;

    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double  q1;
    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    Integer q2;
    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    Integer q3;
    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    Integer q4;
    @Column(columnDefinition = "varchar(50) default NULL")
    String updatedBy;
    @Column(nullable = false, length = 50)
    String updateDate;
    @Column(length = 150)
    String comment;
}

