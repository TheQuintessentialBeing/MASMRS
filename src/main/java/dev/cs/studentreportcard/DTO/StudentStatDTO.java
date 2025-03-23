package dev.cs.studentreportcard.DTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentStatDTO {
    private Integer studentId;
    private String firstName;
    private String lastName;
    private String academicYear;
    private Integer grade;
    private String section;
    private Double semester1Sum;
    private Double semester2Sum;
    private Double totalSum;
    private Integer rank;
    private List<SubjectRecordDTO> subjectRecords; // List of subjects for each student

   }

