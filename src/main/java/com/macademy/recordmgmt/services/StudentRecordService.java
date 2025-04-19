package com.macademy.recordmgmt.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.macademy.recordmgmt.DTO.StudentRecordHeader;
import com.macademy.recordmgmt.models.StudentRecord;
import com.macademy.recordmgmt.repositories.StudentRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final JavaMailSender mailSender; // for mail sending
    @Value("${localCalenderOfEthiopianYear}")
    public int localCalenderOfEthiopianYear;
    @Autowired
    StudentRecordRepository studentRecordRepository;
    @Autowired
    HttpSession httpSession; // to hold object and access later on

    @Autowired
    public StudentRecordService(StudentRecordRepository studentRecordRepository, JavaMailSender mailSender) {
        this.studentRecordRepository = studentRecordRepository;
        this.mailSender              = mailSender;
    }

    public StudentRecordHeader generateStudentGradeReport(Integer studentId, String academicYear) {

        List<Object[]> headers = studentRecordRepository.getAllStudentRecordByYearAndGrade(); // from repository

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
                (r[28] != null) ? ((Number) r[28]).intValue() : null,                        // 29 Q1AllSectionRank
                (r[30] != null) ? ((Number) r[30]).intValue() : null,                        // 30 Q2AllSectionRank
                (r[31] != null) ? ((Number) r[31]).intValue() : null,                        // 31 Q3AllSectionRank
                (r[32] != null) ? ((Number) r[32]).intValue() : null                         // 32 Q4AllSectionRank

        )).collect(Collectors.toList());
        return allStudentRecordHeaders;
    }

    public byte[] prepareReport() throws DocumentException {
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

            // Title
            Paragraph schoolName = new Paragraph("School of Miraf Academy ", FontFactory.getFont(FontFactory.TIMES, 18, BaseColor.BLACK));
            schoolName.setSpacingAfter(20f);
            schoolName.setAlignment(Element.ALIGN_CENTER);
            document.add(schoolName); // writing to the document

            Paragraph reportName = new Paragraph("Detailed Progress Student Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK));
            reportName.setSpacingAfter(20f);
            reportName.setAlignment(Element.ALIGN_CENTER);
            document.add(reportName);

            // adding a header line
            LineSeparator headerLine = new LineSeparator();
            headerLine.setLineColor(BaseColor.BLACK);
            headerLine.setLineWidth(1);
            document.add(headerLine);
            document.add(new Paragraph(Chunk.NEWLINE));

            // student header biograpy
            Font fontOfStudentBiograpy = FontFactory.getFont(FontFactory.TIMES_ITALIC, 12, BaseColor.BLACK);

            // rowOfStudentName
            PdfPTable rowOfStudentName = new PdfPTable(2);
            rowOfStudentName.setWidthPercentage(rowWidthPercentage); // 100 takes full width


            PdfPCell leftCellStudentName = new PdfPCell(new Paragraph("Name: " + bio.getFirstName() + " " + bio.getMiddleName() + " " + bio.getLastName() + " Student Id: " + bio.getStudentId(), fontOfStudentBiograpy));
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

            PdfPCell leftCellStudentDobAndGenderValue = new PdfPCell(new Paragraph("Date of Birth: " + bio.getDateOfBirth() + " Gender: " + bio.getGender(), fontOfStudentBiograpy));
            leftCellStudentDobAndGenderValue.setBorder(Rectangle.NO_BORDER);
            leftCellStudentDobAndGenderValue.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell rightCellSemesterOneRank = new PdfPCell(new Paragraph("Semester I: " + bio.getSemesterOneRank(), fontOfStudentBiograpy));
            rightCellSemesterOneRank.setBorder(Rectangle.NO_BORDER);
            rightCellSemesterOneRank.setHorizontalAlignment(Element.ALIGN_RIGHT);


            rowOfStudentDobAndGender.addCell(leftCellStudentDobAndGenderValue);
            rowOfStudentDobAndGender.addCell(rightCellSemesterOneRank);
            document.add(rowOfStudentDobAndGender);

            PdfPTable rowOfStudentYearAndGrade = new PdfPTable(2);
            rowOfStudentYearAndGrade.setWidthPercentage(rowWidthPercentage); // take full width

            PdfPCell leftCellStudentYearAndGradeValue = new PdfPCell(new Paragraph(" A/Year: " + bio.getAcademicYear() + " Grade: " + bio.getGrade() + bio.getSection(), fontOfStudentBiograpy));
            leftCellStudentYearAndGradeValue.setBorder(Rectangle.NO_BORDER);
            leftCellStudentYearAndGradeValue.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell rightCellStudentSemesterIIValue = new PdfPCell(new Paragraph("Semester II: " + bio.getSemesterTwoRank(), fontOfStudentBiograpy));
            rightCellStudentSemesterIIValue.setBorder(Rectangle.NO_BORDER);
            rightCellStudentSemesterIIValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

            rowOfStudentYearAndGrade.addCell(leftCellStudentYearAndGradeValue);
            rowOfStudentYearAndGrade.addCell(rightCellStudentSemesterIIValue);
            document.add(rowOfStudentYearAndGrade);

            // Adding line break and add another table for the details
            document.add(new Paragraph(Chunk.NEWLINE));
            PdfPTable trows = new PdfPTable(6);
            trows.setWidthPercentage(100); // the table side width

            // Table header
            Stream.of("Subject", "Quarter-I", "Quarter-II", "Quarter-III", "Quarter-IV", "Year Avg.").forEach(h -> {
                PdfPCell c = new PdfPCell(new Phrase(h));
                c.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(c);
            });

            for (StudentRecord r : bio.getDetailrows()) {
                trows.addCell(r.getSubject());
                trows.addCell(String.format("%.1f", r.getQ1())); // format marks like 90.5
                trows.addCell(String.format("%.1f", r.getQ2()));
                trows.addCell(String.format("%.1f", r.getQ3()));
                trows.addCell(String.format("%.1f", r.getQ4()));
                trows.addCell(String.format("%.1f", ((r.getQ1() + r.getQ2() + r.getQ3() + r.getQ4()) / 4.0)));
            }
            double yearlySum = (bio.getQuarterOneSum() + bio.getQuarterTwoSum() + bio.getQuarterThreeSum() + bio.getQuarterFourSum()) / 4.0;

            // sum , average and quarter ranks
            Stream.of("Total", bio.getQuarterOneSum(), bio.getQuarterTwoSum(), bio.getQuarterThreeSum(), bio.getQuarterFourSum(), yearlySum).forEach(d -> {
                PdfPCell k = new PdfPCell(new Phrase(String.valueOf(d)));
                k.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(k);
            });


            Stream.of("Average", bio.getQuarterOneSum() / bio.getNumberOfSubjects(), bio.getQuarterTwoSum() / bio.getNumberOfSubjects(), bio.getQuarterThreeSum() / bio.getNumberOfSubjects(), bio.getQuarterFourSum() / bio.getNumberOfSubjects(), yearlySum / bio.getNumberOfSubjects()).forEach(d -> {
                PdfPCell k = new PdfPCell(new Phrase(String.valueOf(d)));
                k.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(k);
            });


            Stream.of("Section Rank", bio.getQuarterOneRank(), bio.getQuarterTwoRank(), bio.getQuarterThreeRank(), bio.getQuarterFourRank(), bio.getQuarterFourRank()).forEach(d -> {
                PdfPCell k = new PdfPCell(new Phrase(String.valueOf(d)));
                k.setBackgroundColor(BaseColor.LIGHT_GRAY);
                trows.addCell(k);
            });
            Stream.of("All Sec. Rank", bio.getQ1AllSectionRank(), bio.getQ2AllSectionRank(), bio.getQ3AllSectionRank(), bio.getQ4AllSectionRank(), bio.getAllSectionRank()).forEach(d -> {
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

            // Add footer manually at the bottom of the page , footer text position including line break
            // PdfContentByte canvas = writer.getDirectContent();
            PdfContentByte canvas = writer.getDirectContentUnder();
            canvas.setLineWidth(0.75f); // thickness of the line
            canvas.moveTo(30, 60); // (x1,y1) starting point of the line Note Pdf coordinates from bottom_left corner
            canvas.lineTo(559, 60); // ending point of the line so it is a horizontal line starting from x=30 to x=559 at y=60 ( y value should not change for horizontal line)
            // 559-30 then x's length 529 ; note A4 page is 595
            canvas.setColorStroke(BaseColor.RED);// the color of the line
            canvas.stroke(); // line will not appear if this is forgotten


            // Footer Area
            int copyRightFromYear = LocalDate.now().minusYears(localCalenderOfEthiopianYear).getYear();
            LocalDate reportDate = LocalDate.now().minusYears(localCalenderOfEthiopianYear);
            Font footerFont = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10, BaseColor.GRAY);
            String reportFooterCompleteMessage = "Copy right @" + copyRightFromYear + "-" + (copyRightFromYear + 1) + " Developed by SMASR Systems. printed on ... " + reportDate + " E.C ";

            // ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(reportFooterCompleteMessage, footerFont), (document.right() + document.left()) / 4, document.bottom() - 5, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(reportFooterCompleteMessage), 100.0f, 50, 0);
            // close the document that was opened at the begining
            document.close();

            // Send the PDF as a response to controller
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



