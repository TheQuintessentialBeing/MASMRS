package dev.cs.studentreportcard.models;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // make it JPA entity
@Table(name = "Student") // change to any name in database from here
@FieldDefaults(level = AccessLevel.PRIVATE) //make all fields access specifier private
@SequenceGenerator(name = "customerNumber_Seq", initialValue = 1000, allocationSize = 1)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerNumber_Seq")
    @Setter(AccessLevel.PRIVATE)
    Integer studentId;
    //TODo    @Getter(AccessLevel.PRIVATE)
    //TODO    @ToString.Exclude private OtherClassName otherClassName;
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

   /* @Transient
    public int getAge() {

        if (DateOfBirth == null) {
            return 0;//
        } else

            return Period.between(DateOfBirth, LocalDate.now()).getYears();

    }*/

}
