package com.macademy.recordmgmt.controllers;

import com.macademy.recordmgmt.DTO.StudentRecordHeader;
import com.macademy.recordmgmt.repositories.StudentRepository;
import com.macademy.recordmgmt.services.StudentRecordService;
import com.macademy.recordmgmt.utility.MirafUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Controller
@RequestMapping("/record")
public class StudentRecordController {
    // @Autowired creates the beans or objects ( new ...) by spring framework
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final StudentRecordService studentRecordService;


    // This is to hold student data while we move from page to page eg. from search to download
    // search page put data in session variable and download page read from the same variables
    HttpSession session;

    @Autowired
    public StudentRecordController(StudentRecordService studentRecordService, StudentRepository studentRepository, HttpSession session) {

        this.studentRecordService = studentRecordService;
        this.studentRepository    = studentRepository;
        this.session              = session;

    }
    //This method will have an input textboxes for student id and academic year to search for a report.
    // for page should have a search button and a search text, a clickable button

    @GetMapping("/search")
    public String searchStudentRecord() {
        return "rsearch";
    }

    // DONE ? is wildcard and extending Object is optional
    @GetMapping("/search/{studentId}/{academicYear}")
    public String showAllReports(@PathVariable("studentId") Integer studentId, @PathVariable("academicYear") String academicYear, Model model) {
        StudentRecordHeader hr = studentRecordService.generateStudentGradeReport(studentId, academicYear);
        // Add both of them even if they are nulls
        model.addAttribute("header", hr);
        model.addAttribute("detail", (hr != null) ? hr.getDetailrows() : null); // separate from above line b/c thymeleaf was problematic
        model.addAttribute("studentId", studentId);
        model.addAttribute("academicYear", academicYear);
        model.addAttribute("printingDate", MirafUtility.orderDate());
        if (hr == null || hr.getDetailrows() == null) {
            model.addAttribute("errorMessage", "No records found for student Id (" + studentId + ") in academic year (" + academicYear + ") please search with the correct id and academic year");
        }
        // TODO to be checkd if it is needed only here or both here and in service class
        session.setAttribute("hr", hr);
        return "rsearchresult";
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadReport() throws Exception {
        try {
            byte[] pdfBytes = studentRecordService.prepareReport();
            StudentRecordHeader bio = (StudentRecordHeader) session.getAttribute("hr");

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            final String reportName = bio.getFirstName() + "_" + bio.getLastName() + "_" + bio.getStudentId() + "_" + bio.getAcademicYear() + "_" + bio.getGrade() + bio.getSection() + "_" + timestamp;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + reportName + ".pdf");
            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/email")
    public ResponseEntity<String> emailInvoice(Principal principal) throws MessagingException {
        String str;
        try {
            str = studentRecordService.prepareEmail(principal);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
        return ResponseEntity.status(500).body("Error: " + str);
    }
}
/*  @GetMapping("/search/{studentid}{academicyear}")
    public String searchRecords(@PathVariable("studentid") Integer studentId, Model model) {
        System.out.println("/TBDeleted - Testing: /records/search/studendId is hit");
        model.addAttribute("records", studentRecordService.getStudentRecordReport(studentId,academicyear));
       return "rsearchresult";
    }*/


    // DONE ? is wildcard and extending Object is optional
  /*  @GetMapping("post/search/test/{studentId}/{academic_year}")
    public ResponseEntity<? extends Object> showAllReportspost(@PathVariable("studentId") Integer studentId, @PathVariable("academic_year") String academic_year) {
        StudentRecordHeader singleStudentReport = studentRecordService.generateStudentGradeReport(studentId, academic_year);
        if (singleStudentReport == null) {
            return ResponseEntity.ok("No student report for student Id {" + studentId + "} and academic year {" + academic_year);
        }
        return new ResponseEntity<>(singleStudentReport, HttpStatus.OK);
    }
*/
    /*    *//*TODO - Postman test passed localhost:8081/records/search/4 */
    //  @GetMapping("/search/{studentid}")
    // public ResponseEntity<List<StudentRecord>> searchRecords(@PathVariable("studentid") Integer studentId, Model model) {

    //    return new ResponseEntity<>(x, HttpStatus.OK);
    // }

    /* TODO - Testeed for webpage */


    /*
    // Postmat
    @GetMapping("/stat")
    public List<StudentRecordHeader> getStatistics() {
        return studentRecordService.getStudentStatisticsWithRank();
    }



*/
