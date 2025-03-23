package dev.cs.studentreportcard.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // make it JPA entity
@Table(name = "Records") // make table name "Result" in db
@FieldDefaults(level = AccessLevel.PRIVATE) //make all fields access specifier private
@SequenceGenerator(
        name = "recordsNumber_Seq",        // Unique sequence name
        sequenceName = "records_seq",      // Database sequence name
        initialValue = 1001,               // Start value
        allocationSize = 1                 // Increment by 1
)
/*We need to run this on the db as jpa don't create sequence automatically
CREATE TABLE records_seq (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
) AUTO_INCREMENT = 1001;
*/
public class Records {

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
    @ManyToOne
    @JoinColumn( name= "studentId")
    Students student;
    @Column(length = 50)
    String subject;
    @Column(nullable = false, length = 50)
    String academicYear;
    @Min(9)
    @Max(12)
    @Column(nullable = false)
    Integer grade;
    @Column(nullable = false, length = 2)
    String section;

    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double  q1;
    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double q2;
    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double q3;
    @Column( precision = 5, scale = 2)
    @Min(0)
    @Max(100)
    double q4;
    @Column(columnDefinition = "varchar(50) default NULL")
    Integer updatedBy;
    @Column(nullable = true) // null for now till testing
    Date updateDate;
    @Column(length = 150)
    String comment;

}

