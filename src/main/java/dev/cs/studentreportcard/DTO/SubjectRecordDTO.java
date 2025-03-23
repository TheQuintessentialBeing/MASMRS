package dev.cs.studentreportcard.DTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SubjectRecordDTO {


    private String subject;
    private Double q1;
    private Double q2;
    private Double semesterIAverage;
    private Double q3;
    private Double q4;
    private Double semesterIIAverage;

}
