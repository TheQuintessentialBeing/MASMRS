package dev.cs.studentreportcard.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dev.cs.studentreportcard.DTO.StudentRecordHeader;
import dev.cs.studentreportcard.models.StudentRecord;
import dev.cs.studentreportcard.repositories.StudentRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Stream;
@Service
public class StudentRecordService {
    @Autowired
    private final JavaMailSender mailSender;
    @Autowired
    StudentRecordRepository studentRecordRepository;
    @Autowired
    HttpSession httpSession;

    @Autowired
    public StudentRecordService(StudentRecordRepository studentRecordRepository, JavaMailSender mailSender) {
        this.studentRecordRepository = studentRecordRepository;
        this.mailSender              = mailSender;
    }

    public StudentRecordHeader generateStudentGradeReport(Integer studentId, String academicYear) {

        List<Object[]> headers = studentRecordRepository.getAllStudentRecordByYearGradeSection();

        // mapping fields of Object[] to List<> collection
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
        studentReport.setDetailrows(listOfStudentRecordForAcademicYear);
        httpSession.setAttribute("hr", studentReport);
        return studentReport;
    }

    public List<StudentRecord> findStudentRecordByIdAndAcademicYear(Integer studentId, String academicYear) {
        return studentRecordRepository.findStudentRecordByIdAndAcademicYear(studentId, academicYear);

    }

    public List<StudentRecordHeader> getAllStudentRecordHeaders(List<Object[]> headers) {
        //  TODO we need to decide on : -
        //  gender and section can be a single character to save database size
        // another option is limit them to 1 and see if they have value of string or character and cast accordingly
        List<StudentRecordHeader> allStudentRecordHeaders = headers.stream().map(r -> new StudentRecordHeader((Integer) r[0],                                                              // 0 s.student_id
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
                null,                                               // 23 s.photo
                (r[24] != null) ? ((Boolean) r[24]).booleanValue() : null,                   // 24 s.is_Active
                (r[25] != null) ? ((Number) r[25]).intValue() : null,                        // 25
                (r[26] != null) ? ((Number) r[26]).intValue() : null,                        // 26
                (r[27] != null) ? ((Number) r[27]).intValue() : null,                        // 27
                (r[28] != null) ? ((Number) r[28]).intValue() : null,                        // 28
                (r[28] != null) ? ((Number) r[28]).intValue() : null,                        // 28
                (r[30] != null) ? ((Number) r[30]).intValue() : null,                        // 28
                (r[31] != null) ? ((Number) r[31]).intValue() : null,                        // 28
                (r[32] != null) ? ((Number) r[32]).intValue() : null                         // 28

        )).collect(Collectors.toList());
        return allStudentRecordHeaders;
    }

    public byte[] prepareReport() throws DocumentException {
        // TODO can we move this code to a service - we need to make sure the session is sharable b/n search and dow
       /* response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=sales-report.pdf");
        */

        // this method is to prepare a pdf report and add a functionality to download
        byte[] pdfBytes = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            // Create a PDF document (using iText or similar library) requires adding IText in pom.xml file
            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Fetch the report data from a session (
            StudentRecordHeader bio = (StudentRecordHeader) httpSession.getAttribute("hr");

            // Define custom font
            //Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLUE);
            //Chunk nameChunk = new Chunk("Name: John Doe", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));

            // Title
            Paragraph reportTitle = new Paragraph("School of Mieraf Academy Student's Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK));
            reportTitle.setSpacingAfter(20f);
            reportTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(reportTitle);


            // sub titles
            Font subtitle = FontFactory.getFont(FontFactory.TIMES_ITALIC, 12, BaseColor.BLACK);

            // row 1
            PdfPTable row1 = new PdfPTable(2);
            row1.setWidthPercentage(100); // take full width

            // Remove borders (optional)
            PdfPCell leftCell1 = new PdfPCell(new Paragraph(bio.getFirstName() + " " + bio.getMiddleName() + " " + bio.getLastName(), subtitle));
            leftCell1.setBorder(Rectangle.NO_BORDER);
            leftCell1.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell rightcell2 = new PdfPCell(new Paragraph("Rank : " + bio.getSemesterOneRank(), subtitle));
            rightcell2.setBorder(Rectangle.NO_BORDER);
            rightcell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

            row1.addCell(leftCell1);
            row1.addCell(rightcell2);
            document.add(row1);

            // row 2
            PdfPTable row2 = new PdfPTable(2);
            row2.setWidthPercentage(100); // take full width

            // Remove borders (optional)
            PdfPCell leftCell3 = new PdfPCell(new Paragraph("DoB:" + bio.getDateOfBirth() + " Gender: " + bio.getGender(), subtitle));
            leftCell3.setBorder(Rectangle.NO_BORDER);
            leftCell3.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell rightcell4 = new PdfPCell(new Paragraph("Semester I Rank : " + bio.getSemesterOneRank(), subtitle));
            rightcell4.setBorder(Rectangle.NO_BORDER);
            rightcell4.setHorizontalAlignment(Element.ALIGN_RIGHT);


            row2.addCell(leftCell3);
            row2.addCell(rightcell4);
            document.add(row2);

            // row 3
            PdfPTable row3 = new PdfPTable(2);
            row2.setWidthPercentage(100); // take full width

            // Remove borders (optional)
            PdfPCell leftCell5 = new PdfPCell(new Paragraph(" Academic Year :" + bio.getAcademicYear() + "Grade:" + bio.getGrade() + bio.getSection(), subtitle));
            leftCell5.setBorder(Rectangle.NO_BORDER);
            leftCell5.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell rightcell6 = new PdfPCell(new Paragraph("Semester II Rank: " + bio.getSemesterTwoRank(), subtitle));
            rightcell6.setBorder(Rectangle.NO_BORDER);
            rightcell6.setHorizontalAlignment(Element.ALIGN_RIGHT);

            row3.addCell(leftCell5);
            row3.addCell(rightcell6);
            document.add(row3);


            // Adding line break
            document.add(new Paragraph(Chunk.NEWLINE));

            // Create table with three column TODO make dynamic later on
            PdfPTable trows = new PdfPTable(6);
            trows.setWidthPercentage(100);

            // Table header
            Stream.of("Subject", "Quarter-I", "Quarter-II", "Quarter-III", "Quarter-IV", "Year Avg.").forEach(h -> {
                PdfPCell c = new PdfPCell(new Phrase(h));
                c.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(c);
            });

            for (StudentRecord r : bio.getDetailrows()) {
                trows.addCell(r.getSubject());
                trows.addCell(String.format("%.1f", r.getQ1()));
                trows.addCell(String.format("%.1f", r.getQ2()));
                trows.addCell(String.format("%.1f", r.getQ3()));
                trows.addCell(String.valueOf(r.getQ4()));
                trows.addCell(String.valueOf((r.getQ1() + r.getQ2() + r.getQ3() + r.getQ4()) / 4.0));
            }
            double totalSum = (bio.getQuarterFourSum() + bio.getQuarterTwoSum() + bio.getQuarterThreeSum() + bio.getQuarterFourSum()) / 4.0;
            // sum and quarter ranks
            Stream.of("Total", bio.getQuarterOneSum(), bio.getQuarterTwoSum(), bio.getQuarterThreeSum(), bio.getQuarterFourSum(), totalSum).forEach(d -> {
                PdfPCell k = new PdfPCell(new Phrase(String.valueOf(d)));
                k.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(k);
            });
            Stream.of("Sec Rank", bio.getQuarterOneRank(), bio.getQuarterTwoRank(), bio.getQuarterThreeRank(), bio.getQuarterFourRank(), 0).forEach(d -> {
                PdfPCell k = new PdfPCell(new Phrase(String.valueOf(d)));
                k.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(k);
            });
            Stream.of("All Sec Rank", bio.getQ1AllSectionRank(), bio.getQ2AllSectionRank(), bio.getQ3AllSectionRank(), bio.getQ4AllSectionRank(), bio.getAllSectionRank()).forEach(d -> {
                PdfPCell k = new PdfPCell(new Phrase(String.valueOf(d)));
                k.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(k);
            });
            Stream.of("# of Student", bio.getQ1StudentCount(), bio.getQ2StudentCount(), bio.getQ3StudentCount(), bio.getQ4StudentCount(), bio.getQ4StudentCount()).forEach(d -> {
                PdfPCell k = new PdfPCell(new Phrase(String.valueOf(d)));
                k.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(k);
            });
            document.add(trows);
            document.add(Chunk.NEWLINE);
            Paragraph p = new Paragraph("Report generated on " + new java.util.Date(), FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10, BaseColor.GRAY));
            document.add(p);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Disclaimer : \n \t \tPrinting this report without the proper access, is violation of privacy.", FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10, BaseColor.GRAY)));
            document.add(new Paragraph(""));
            document.add(new Paragraph(" @2025 Report generated by School of Mieraf Academy Student Records & Management Systems.", FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10, BaseColor.GRAY)));
            document.close();

            // Send the PDF as a response
            pdfBytes = byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdfBytes;

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



