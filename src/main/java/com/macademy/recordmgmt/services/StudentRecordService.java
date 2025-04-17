package com.macademy.recordmgmt.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.macademy.recordmgmt.DTO.StudentRecordHeader;
import com.macademy.recordmgmt.models.StudentRecord;
import com.macademy.recordmgmt.repositories.StudentRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.security.Principal;
import java.time.LocalDate;
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
        // Ethiopian calander adjustment - it could be further analayzed for months or use Ethiopian Calander API
        // we can also add this in properties file
        final int ethiopianCalenderAdjustment = 8;
        int copyRightFromYear = LocalDate.now().minusYears(ethiopianCalenderAdjustment).getYear();
        LocalDate reportDate = LocalDate.now().minusYears(ethiopianCalenderAdjustment);
        Font footerFont = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10, BaseColor.GRAY);
        String reportFooterCompleteMessage = "Copy Right @ " + copyRightFromYear + " - " + (copyRightFromYear + 1) + " by SMASR & Management Systems. Report generated date " + reportDate + " E.C ";

        // this method is to prepare a pdf report and add a functionality to download
        byte[] pdfBytes = null;
        final int rowWidthPercentage = 100;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            // Create a PDF document (using iText or similar library) requires adding IText in pom.xml file
            var document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Fetch the student record header report data from a session set by records/search page
            StudentRecordHeader bio = (StudentRecordHeader) httpSession.getAttribute("hr");

            // Define custom font
            // Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLUE);
            // Chunk nameChunk = new Chunk("Name: John Doe", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));

            // Title
            Paragraph schoolName = new Paragraph("School of Mieraf Academy ", FontFactory.getFont(FontFactory.TIMES, 18, BaseColor.BLACK));
            // schoolName.setSpacingAfter(20f);
            schoolName.setAlignment(Element.ALIGN_CENTER);
            document.add(schoolName);

            Paragraph reportName = new Paragraph("Detailed Progress Student Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK));
            reportName.setSpacingAfter(20f);
            reportName.setAlignment(Element.ALIGN_CENTER);
            document.add(reportName);


            //SolidBorder solidBorder = new SolidBorder(2);
            // adding a header line
            LineSeparator lineSeparator = new LineSeparator();
            lineSeparator.setLineColor(BaseColor.BLACK);
            lineSeparator.setLineWidth(2);
            document.add(lineSeparator);
            document.add(new Paragraph(Chunk.NEWLINE));

            // student header biograpy
            Font fontOfStudentBiograpy = FontFactory.getFont(FontFactory.TIMES_ITALIC, 12, BaseColor.BLACK);

            // rowOfStudentName
            PdfPTable rowOfStudentName = new PdfPTable(2);
            rowOfStudentName.setWidthPercentage(rowWidthPercentage); // 100 takes full width


            PdfPCell leftCellStudentName = new PdfPCell(new Paragraph(bio.getFirstName() + " " + bio.getMiddleName() + " " + bio.getLastName(), fontOfStudentBiograpy));
            // Remove borders (optional)
            leftCellStudentName.setBorder(Rectangle.NO_BORDER);
            leftCellStudentName.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell rightCellRanksText = new PdfPCell(new Paragraph("Rank(s)", fontOfStudentBiograpy));
            rightCellRanksText.setBorder(Rectangle.NO_BORDER);
            rightCellRanksText.setHorizontalAlignment(Element.ALIGN_RIGHT);

            rowOfStudentName.addCell(leftCellStudentName);
            rowOfStudentName.addCell(rightCellRanksText);
            document.add(rowOfStudentName);

            // rowOfStudentDobAndGender
            PdfPTable rowOfStudentDobAndGender = new PdfPTable(2);
            rowOfStudentDobAndGender.setWidthPercentage(rowWidthPercentage); // take full width

            // Remove borders (optional)
            PdfPCell leftCellStudentDobAndGenderValue = new PdfPCell(new Paragraph("DoB:" + bio.getDateOfBirth() + " Gender: " + bio.getGender(), fontOfStudentBiograpy));
            leftCellStudentDobAndGenderValue.setBorder(Rectangle.NO_BORDER);
            leftCellStudentDobAndGenderValue.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell rightCellSemesterOneRank = new PdfPCell(new Paragraph("Semester I: " + bio.getSemesterOneRank(), fontOfStudentBiograpy));
            rightCellSemesterOneRank.setBorder(Rectangle.NO_BORDER);
            rightCellSemesterOneRank.setHorizontalAlignment(Element.ALIGN_RIGHT);


            rowOfStudentDobAndGender.addCell(leftCellStudentDobAndGenderValue);
            rowOfStudentDobAndGender.addCell(rightCellSemesterOneRank);
            document.add(rowOfStudentDobAndGender);

            // row 3
            PdfPTable rowOfStudentYearAndGrade = new PdfPTable(2);
            rowOfStudentYearAndGrade.setWidthPercentage(rowWidthPercentage); // take full width

            // Remove borders (optional)
            PdfPCell leftCellStudentYearAndGradeValue = new PdfPCell(new Paragraph(" A/Year :" + bio.getAcademicYear() + "Grade:" + bio.getGrade() + bio.getSection(), fontOfStudentBiograpy));
            leftCellStudentYearAndGradeValue.setBorder(Rectangle.NO_BORDER);
            leftCellStudentYearAndGradeValue.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell rightCellStudentSemesterIIValue = new PdfPCell(new Paragraph("Semester II: " + bio.getSemesterTwoRank(), fontOfStudentBiograpy));
            rightCellStudentSemesterIIValue.setBorder(Rectangle.NO_BORDER);
            rightCellStudentSemesterIIValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

            rowOfStudentYearAndGrade.addCell(leftCellStudentYearAndGradeValue);
            rowOfStudentYearAndGrade.addCell(rightCellStudentSemesterIIValue);
            document.add(rowOfStudentYearAndGrade);

            // Adding line break
            // document.add(new Paragraph(Chunk.NEWLINE));

            // Create table with three column TODO make dynamic later on
            PdfPTable trows = new PdfPTable(6);
            trows.setWidthPercentage(100);
            trows.setSummary("How can I add a summary ?");
            trows.setSkipFirstHeader(true);
            trows.setSpacingBefore(4);

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
            Stream.of("Section rank", bio.getQuarterOneRank(), bio.getQuarterTwoRank(), bio.getQuarterThreeRank(), bio.getQuarterFourRank(), 0).forEach(d -> {
                PdfPCell k = new PdfPCell(new Phrase(String.valueOf(d)));
                k.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(k);
            });
            Stream.of("All section rank", bio.getQ1AllSectionRank(), bio.getQ2AllSectionRank(), bio.getQ3AllSectionRank(), bio.getQ4AllSectionRank(), bio.getAllSectionRank()).forEach(d -> {
                PdfPCell k = new PdfPCell(new Phrase(String.valueOf(d)));
                k.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(k);
            });
            Stream.of("Total students", bio.getQ1StudentCount(), bio.getQ2StudentCount(), bio.getQ3StudentCount(), bio.getQ4StudentCount(), bio.getQ4StudentCount()).forEach(d -> {
                PdfPCell k = new PdfPCell(new Phrase(String.valueOf(d)));
                k.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(k);
            });
            document.add(trows);
            document.add(Chunk.NEWLINE);

            // Add footer manually at the bottom of the page
            PdfContentByte canvas = writer.getDirectContent();
            // Footer text
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(reportFooterCompleteMessage, footerFont), (document.right() + document.left()) / 4, document.bottom() - 30, 0);
            // close the document that was opened at the begining
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



