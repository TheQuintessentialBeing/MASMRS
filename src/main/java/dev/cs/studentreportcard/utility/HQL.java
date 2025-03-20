package dev.cs.studentreportcard.utility;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
@NamedQueries({
        @NamedQuery(name = "SqlSearchStudentByStudentId", query = "FROM Student p where p.StudentId=:studentid"),
        @NamedQuery(name = "sqlDeleteStudentByStudentId", query = "DELETE FROM Student p where p.studentId =:studentId"), // check param names here
})
public class HQL {
}