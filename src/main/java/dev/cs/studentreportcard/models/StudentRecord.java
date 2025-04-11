package dev.cs.studentreportcard.models;

import com.itextpdf.text.pdf.PdfPCell;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // make it JPA entity
@Table(name = "StudentRecords") // make table name "Result" in db
@FieldDefaults(level = AccessLevel.PRIVATE) //make all fields access specifier private
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
public class StudentRecord { /*TODO entities shall be singular but db names shall be plural*/
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
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
    @Column(nullable = false) // TODO this could be manytoOne or use the table design we drafted.
    Integer updatedBy;
    @Column(nullable = true) // TODO this should nullable=false and shoud pick today's date while the user enters data on the page/form
    Date updateDate;
    @Column(length = 150)
    String comment;

   // @ManyToOne // StudentRecord(many records) will match (One Student) Remember : Student Id must exist in Student Table before it is used/insert in StudentRecord table
   // @JoinColumn( name= "FK_StudentId", referencedColumnName = "studentId")// in production this should be set , nullable = true)
    // FK_StudentId is name of the foreign key
    // studentId is the primary key of the parent table i,e Student
    // Student student;
    // studnet is here to navigate b/n the tables




    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setQ1(double q1) {
        this.q1 = q1;
    }
    public double getQ1() {
        return q1;
    }
    public double getQ2() {
        return q2;
    }



    public void setQ2(double q2) {
        this.q2 = q2;
    }

    public double getQ3() {
        return q3;
    }

    public void setQ3(double q3) {
        this.q3 = q3;
    }

    public double getQ4() {
        return q4;
    }

    public void setQ4(double q4) {
        this.q4 = q4;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}

