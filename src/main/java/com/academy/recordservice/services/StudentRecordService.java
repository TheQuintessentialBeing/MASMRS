package com.academy.recordservice.services;

import com.academy.recordservice.DTO.StudentRecordHeader;
import com.academy.recordservice.models.StudentRecord;
import com.academy.recordservice.repositories.StudentRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentRecordService {
    @Autowired
    private final JavaMailSender mailSender; // for mail sending
    private final StudentRecordRepository studentRecordRepository;
    @Value("${localCalenderOfEthiopianYear}")
    public int localCalenderOfEthiopianYear;
    @Autowired
    HttpSession httpSession; // to hold object and access later on

    @Autowired
    public StudentRecordService(StudentRecordRepository studentRecordRepository, JavaMailSender mailSender) {
        this.studentRecordRepository = studentRecordRepository;
        this.mailSender              = mailSender;
    }

    public StudentRecordHeader generateStudentGradeReport(Integer studentId, String academicYear) {
        System.out.println("hitting the student record header...");
        List<Object[]> headers = studentRecordRepository.getAllStudentRecordByYearAndGrade(); // from repository
        System.out.println("headers" + headers);
        // mapping fields of Object[] to List<StudentRecordHeader> collection
        List<StudentRecordHeader> allHeaders = getAllStudentRecordHeaders(headers);

        if (allHeaders == null)
            return null;

        // Searching the StudentRecordHeader for student ID
        List<StudentRecord> listOfStudentRecordForAcademicYear = findStudentRecordByIdAndAcademicYear(studentId, academicYear);

        if (listOfStudentRecordForAcademicYear == null)
            return null;

        StudentRecordHeader studentReport = allHeaders.stream().filter(o -> Objects.equals(o.getStudentId(), studentId) && Objects.equals(o.getAcademicYear(), academicYear)).findFirst().orElse(null);

        // listOfStudentRecordForAcademicYear is don't have detail records so attaching it is important
        if (studentReport == null)
            return null;
        // studentReport.setDetailrows(listOfStudentRecordForAcademicYear);
        httpSession.setAttribute("hr", studentReport);
        return studentReport;
    }

    public List<StudentRecord> findStudentRecordByIdAndAcademicYear(Integer studentId, String academicYear) {
        return studentRecordRepository.findStudentRecordByIdAndAcademicYear(studentId, academicYear);

    }

    public List<StudentRecordHeader> getAllStudentRecordHeaders(List<Object[]> headers) {
        //  TODO we need to decide on : -
        //  gender and section can be a single character to save database size
        //  another option is limit them to 1 and see if they have value of string or character and cast accordingly
        List<StudentRecordHeader> allStudentRecordHeaders = headers.stream().map(r -> new StudentRecordHeader((Integer) r[0],                                                              // 0 s.student_id
                (r[1] != null) ? (String) r[1] : null,                                       // 1 s.first_name // if first name is not null then give me fisrt name else (is null) give me null
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
                // (r[23] != null) ? (String) r[23] : null,                                     // 23 s.photo
                null,                                     // 23 s.photo
                (r[24] != null) ? ((Boolean) r[24]).booleanValue() : null,                   // 24 s.is_Active
                (r[25] != null) ? ((Number) r[25]).intValue() : null,                        // 25 Q1StudentCount
                (r[26] != null) ? ((Number) r[26]).intValue() : null,                        // 26 Q2StudentCount
                (r[27] != null) ? ((Number) r[27]).intValue() : null,                        // 27 Q3StudentCount
                (r[28] != null) ? ((Number) r[28]).intValue() : null,                        // 28 Q4TotalStudents
                (r[29] != null) ? ((Number) r[29]).intValue() : null,                        // 29 Q1AllSectionRank
                (r[30] != null) ? ((Number) r[30]).intValue() : null,                        // 30 Q2AllSectionRank
                (r[31] != null) ? ((Number) r[31]).intValue() : null,                        // 31 Q3AllSectionRank
                (r[32] != null) ? ((Number) r[32]).intValue() : null                         // 32 Q4AllSectionRank

        )).collect(Collectors.toList());
        return allStudentRecordHeaders;
    }

    public String prepareEmail(Principal principal) {
        try {
            // Step 1: Get logged-in user's email
            String toEmail = principal.getName(); // or fetch from DB using username

            // Step 2: Generate the PDF
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // myPdfService.generateInvoicePdf(baos);
            // ResponseEntity<byte[]> x = downloadReport();
            // byte[] pdfBytes = baos.toByteArray();

            // Step 3: Send it
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Your Invoice");
            helper.setText("Hello, please find your invoice attached.", true);

            // helper.addAttachment("invoice.pdf", new ByteArrayResource(x));
            // helper.addAttachment("invoce", x);
            mailSender.send(message);
            return "Email sent!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}



