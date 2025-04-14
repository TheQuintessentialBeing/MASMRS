package dev.cs.studentreportcard.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
<<<<<<< HEAD
import java.sql.Date;
import java.time.LocalDate;
=======
>>>>>>> b5d5a97 (modified classes, added html)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // make it JPA entity
@Table(name = "Records") // make table name "Result" in db
@FieldDefaults(level = AccessLevel.PRIVATE) //make all fields access specifier private
<<<<<<< HEAD
@SequenceGenerator(
        name = "recordsNumber_Seq",        // Unique sequence name
        sequenceName = "records_seq",      // Database sequence name
        initialValue = 1001,               // Start value
        allocationSize = 1                 // Increment by 1
)
/*TODO Tobe aware We need to run this on the db as jpa don't create sequence automatically
CREATE TABLE records_seq (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
) AUTO_INCREMENT = 1001;
*/
public class Records { /*TODO entities shall be singular but db names shall be plural*/

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recordsNumber_Seq"
    )
    @Setter(AccessLevel.PRIVATE)
    private Integer recordsId;

    //FK - Relationship TODO
    @Setter
    @Getter
    @ManyToOne // Records(many records) will match (One Student) Remember : Student Id must exist in Students Table before it is used/insert in Records table
    @JoinColumn( name= "studentId")
    Students student;
=======
@SequenceGenerator(name = "recordsNumber_Seq", initialValue = 1000, allocationSize = 1)
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recordsNumber_Seq")
    @Setter(AccessLevel.PRIVATE)
    Integer recordsId;
    //FK - Relationship TODO
    Integer studentId;
>>>>>>> b5d5a97 (modified classes, added html)
    @Column(length = 50)
    String subject;
    @Column(nullable = false, length = 50)
    String academicYear;
<<<<<<< HEAD
    @Min(9)
    @Max(12)
    @Column(nullable = false)
    Integer grade;
    @Column(nullable = false, length = 2)
    String section;
=======
    @Column(nullable = false, length = 1)
    String grade;
    @Column(nullable = false, length = 2)
    String section;

>>>>>>> b5d5a97 (modified classes, added html)
    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double  q1;
    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
<<<<<<< HEAD
    double q2;
    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double q3;
    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double q4;
    @Column(nullable = false) // TODO this could be manytoOne or use the table design we drafted.
    Integer updatedBy;
    @Column(nullable = true) // TODO this should nullable=false and shoud pick today's date while the user enters data on the page/form
    Date updateDate;
    @Column(length = 150)
    String comment;

=======
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
>>>>>>> b5d5a97 (modified classes, added html)
}

