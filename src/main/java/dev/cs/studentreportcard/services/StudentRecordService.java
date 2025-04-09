package dev.cs.studentreportcard.services;

import dev.cs.studentreportcard.DTO.StudentRecordHeader;
import dev.cs.studentreportcard.models.StudentRecord;
import dev.cs.studentreportcard.repositories.StudentRecordRepository;
import dev.cs.studentreportcard.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.stream.Collectors;
@Service
public class StudentRecordService {
    @Autowired
    StudentRecordRepository studentRecordRepository;

    @Autowired
    //StudentRepository studentRepository;
    public StudentRecordService(StudentRecordRepository studentRecordRepository,
                                StudentRepository studentRepository) {
        this.studentRecordRepository = studentRecordRepository;
        //this.studentRepository = studentRepository;
    }

    public StudentRecordService() {
    }

    public Page<StudentRecord> listAllRecords(PageRequest pageRequest) {

        return studentRecordRepository.findAll(pageRequest);
    }


    public StudentRecordHeader generateStudentGradeReport(Integer studentId, String academicYear) {

        List<Object[]> headers = studentRecordRepository.findRecordsByYearGradeAndSection();

        // mapping fields of Object[] to List<> collection
        List<StudentRecordHeader> allHeaders = getAllStudentRecordHeaders(headers);

        if (allHeaders == null) return null;

        // Searching the StudentRecordHeader for student ID
        List<StudentRecord> listOfStudentRecordForAcademicYear = findStudentRecordByIdAndAcademicYear(studentId, academicYear);

        if (listOfStudentRecordForAcademicYear == null) return null;

        StudentRecordHeader studentReport = allHeaders.stream().filter(o -> Objects.equals(o.getStudentId(), studentId) &&
                Objects.equals(o.getAcademicYear(), academicYear)).findFirst().orElse(null);

        // listOfStudentRecordForAcademicYear is don't have detail records so attaching it is important
        if (studentReport == null) return null;
        studentReport.setDetailrows(listOfStudentRecordForAcademicYear);
        return studentReport;
    }

    public List<StudentRecord> findStudentRecordByIdAndAcademicYear(Integer studentId, String academicYear) {
        return studentRecordRepository.findStudentRecordByIdAndAcademicYear(studentId, academicYear);

    }

    public List<StudentRecordHeader> getAllStudentRecordHeaders(List<Object[]> headers) {
        // TODO we need to decide on : -
        //  gender and section can be a single character to save database size
        // another option is limit them to 1 and see if they have value of string or character and cast accordingly
        List<StudentRecordHeader> allStudentRecordHeaders = headers.stream().map(r -> new StudentRecordHeader(
                (Integer) r[0],                                                              // 0 s.student_id
                (r[1] != null) ? (String) r[1] : null,                                       // 1 s.first_name
                (r[2] != null) ? (String) r[2] : null,                                       // 2 s.middle_name
                (r[3] != null) ? (String) r[3] : null,                                       // 3 s.last_name
                (Date) r[4],                                                                 // 4 s.date_of_birth
                (r[5] instanceof Character) ? (Character) r[5] : ((String) r[5]).charAt(0),  // 5 s.gender
                (String) r[6],                                                               // 6 r.academic_year
                (r[7] != null) ? ((Number) r[7]).intValue() : null,                          // 7 r.grade
                (String) r[8],                                                               // 8 r.section
                (r[9] != null) ? ((Number) r[9]).intValue() : null,                          // 9 count(*)      NumberOfSubjects,
                (r[10] != null) ? ((Number) r[10]).doubleValue() : null,                     // 10 sum(r.q1)    Q1Sum
                (r[11] != null) ? ((Number) r[11]).intValue() : null,                        // 11 RANK()       Q1Rank,
                (r[12] != null) ? ((Number) r[12]).doubleValue() : null,                     // 12 sum( r.q2)   Q2Sum
                (r[13] != null) ? ((Number) r[13]).intValue() : null,                        // 13 RANK()       Q2Rank
                (r[14] != null) ? ((Number) r[14]).intValue() : null,                        // 14 RANK() )     Sem1Rank
                (r[15] != null) ? ((Number) r[15]).doubleValue() : null,                     // 15 sum(r.q3)    Q3Sum
                (r[16] != null) ? ((Number) r[16]).intValue() : null,                        // 16 DENSE_RANK() Q3Rank
                (r[17] != null) ? ((Number) r[17]).doubleValue() : null,                     // 17 sum( r.q4)   Q4Sum
                (r[18] != null) ? ((Number) r[18]).intValue() : null,                        // 18 DENSE_RANK() Q4Rank
                (r[19] != null) ? ((Number) r[19]).intValue() : null,                        // 19 DENSE_RANK() Sem2Rank
                (r[20] != null) ? ((Number) r[20]).intValue() : null,                        // 20 DENSE_RANK() OverAllRank
                (r[21] != null) ? ((Number) r[21]).intValue() : null,                        // 21 COUNT(*)     TotalNumber
                (r[22] != null) ? ((Number) r[22]).intValue() : null,                        // 22 NTILE()      Over25NTileRank
                (r[23] != null) ? null : null,                                               // 23 s.photo
                (r[24] != null) ? ((Boolean) r[24]).booleanValue() : null,                   // 24 s.is_Active
                (r[25] != null) ? ((Number) r[25]).intValue() : null,                         //
                (r[26] != null) ? ((Number) r[26]).intValue() : null,                         //
                (r[27] != null) ? ((Number) r[27]).intValue() : null,                         //
                (r[28] != null) ? ((Number) r[28]).intValue() : null                          //
        )).collect(Collectors.toList());
        return allStudentRecordHeaders;
    }
}



