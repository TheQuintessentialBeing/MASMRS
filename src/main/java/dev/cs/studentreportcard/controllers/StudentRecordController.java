package dev.cs.studentreportcard.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dev.cs.studentreportcard.DTO.StudentRecordHeader;
import dev.cs.studentreportcard.models.StudentRecord;
import dev.cs.studentreportcard.repositories.StudentRepository;
import dev.cs.studentreportcard.services.StudentRecordService;
import dev.cs.studentreportcard.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.security.Principal;
import java.util.stream.Stream;

@Controller
@RequestMapping("/records")
public class StudentRecordController {
    // @Autowired creates the beans or objects ( new ...) by spring framework
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final StudentRecordService studentRecordService;
    @Autowired
    private final JavaMailSender mailSender;

    // This is to hold student data while we move from page to page eg. search to download
    // search page put data in session variable and download page read from the variables
    HttpSession session;

    @Autowired
    public StudentRecordController(StudentRecordService studentRecordService, StudentRepository studentRepository, HttpSession session, JavaMailSender mailSender) {

        this.studentRecordService = studentRecordService;
        this.studentRepository    = studentRepository;
        this.session              = session;
        this.mailSender           = mailSender;

    }

    /* //This method will have an input textboxes for student id and academic year to search for a report.
       // for page should have a search button and a search text, a clickable button
     */
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
        model.addAttribute("detail", (hr != null) ? hr.getDetailrows() : null);
        model.addAttribute("studentId", studentId);
        model.addAttribute("academicYear", academicYear);
        model.addAttribute("printingDate", Util.orderDate());
        if (hr == null || hr.getDetailrows() == null) {
            model.addAttribute("errorMessage", "No records found for student Id (" + studentId + ") in academic year (" + academicYear + ") please search with the correct id and academic year");
        }
        session.setAttribute("hr", hr);
        //session.setAttribute("dt",hr.getDetailrows());
        return "rsearchresult";
    }

    @GetMapping("/report/download")
    public ResponseEntity<byte[]> downloadReport() throws Exception {
       /* response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=sales-report.pdf");
        */

        // this method is to prepare a pdf report and add a functionality to download
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            // Create a PDF document (using iText or similar library) requires adding IText in pom.xml file
            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Fetch the report data from a session (
            StudentRecordHeader bio = (StudentRecordHeader) session.getAttribute("hr");
            final String reportName = bio.getFirstName() + bio.getLastName() + "_" + bio.getStudentId() + "_" + bio.getAcademicYear() + bio.getGrade() + bio.getSection();

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
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Disposition", "attachment; filename=" + reportName + ".pdf");

            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/email-invoice")
    public ResponseEntity<String> emailInvoice(Principal principal) throws MessagingException {
        {
            try {
                // Step 1: Get logged-in user's email
                String toEmail = principal.getName(); // or fetch from DB using username

                // Step 2: Generate the PDF
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // myPdfService.generateInvoicePdf(baos);
                ResponseEntity<byte[]> x = downloadReport();
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
                return ResponseEntity.ok("Email sent!");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error: " + e.getMessage());
            }
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


    /*
    @GetMapping("index")
    public String showAllRecords(HttpServletRequest request, Model model) {
       int page = 0;
        int size = 5;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("records", studentRecordService.listAllRecords(PageRequest.of(page,size)));

        System.out.println("Test records");
       return "recordslist";
       // return "index";
    }*/

/*
    @GetMapping("/page")
    public String showAllProductByPage(HttpServletRequest request, @RequestParam("page") int page, Model model) {
        int size = 5;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("products", productService.listAllProducts(PageRequest.of(page, size)));
        return "productlist";
    }

    // dispaly products by page number
    @GetMapping("/detail/{productcode}")
    public String showProductDetail(@PathVariable String productcode, Model model) {
        model.addAttribute("product", productService.getProductByProductCode(productcode));
        return "productdetail";
    }
    */
/*

    @GetMapping("/cart/{productcode}")
    public void addItemToCart(@PathVariable String productcode, HttpServletResponse response) throws IOException {
        productService.addItemToVirtualCart(productcode);
        productService.decreaseStockQuantity(productcode);
        response.sendRedirect("/product/");
    }
    */
/*
    // customers can see what they have in their cart
    @GetMapping("/mycart")
    public String showItemInCart(Model model) {
        List<ProductVirtualCartDTO> obj = productService.listAllCartItems();
        if (obj != null) {
            model.addAttribute("products", productService.listAllCartItems());
        }
        model.addAttribute("total", productService.totalCharges());
        return "productcart";

    }
    // customers can see what they have in their cart
    @GetMapping("/emptycart")
    public void cleanVirtualCart(HttpServletResponse response) throws IOException {
        productService.clearVirtualCart();
        response.sendRedirect("/product/mycart");
    }
    // customers car remove products from cart
    @GetMapping("/removeproduct/{productcode}")
    public void removeItemfromCart(HttpServletResponse response, @PathVariable String productcode) throws IOException {
        productService.removeItemFromVirtualCart(productcode);
        response.sendRedirect("/product/mycart");
    }
    @GetMapping("/admin")
    public String productDashboard(HttpServletRequest request, Model model) {


        int page = 0;
        int size = 5;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("products", productService.listAllProducts(PageRequest.of(page, size)));
        return "adminproduct";
    }
    @GetMapping("/admin/page")
    public String productDashboardByPage(HttpServletRequest request, @RequestParam("page") int page, Model model) {
        int size = 5;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("products", productService.listAllProducts(PageRequest.of(page, size)));
        return "adminproduct";
    }
    // Add
    @GetMapping("/cart/reduce/{productcode}")
    public String reduceCartQuantity(@PathVariable String productcode, Model model) throws IOException {
        productService.reduceQuantityFromVirtualCart(productcode);
        productService.increaseStockQuantity(productcode);
        List<ProductVirtualCartDTO> obj = productService.listAllCartItems();
        if (obj != null) {
            model.addAttribute("products", productService.listAllCartItems());
        }
        model.addAttribute("total", productService.totalCharges());
        return "productcart";
    }
    @GetMapping("/cart/more/{productcode}")
    public String moreCartQuantity(@PathVariable String productcode, Model model) throws IOException {
        Product underChange = productService.getProductByProductCode(productcode);
        productService.addItemToVirtualCart(productcode);
        productService.decreaseStockQuantity(productcode);
        List<ProductVirtualCartDTO> obj = productService.listAllCartItems();
        if (obj != null) {
            model.addAttribute("products", productService.listAllCartItems());
        }
        model.addAttribute("total", productService.totalCharges());
        return "productcart";
    }
    @GetMapping("/cart/remove/{productcode}")
    public String removeProductFromCart(@PathVariable String productcode, Model model) throws IOException {
        short returnQuantity = productService.getQuantityFromVirtualCart(productcode);
        productService.increaseStockQuantityBatch(returnQuantity, productcode);
        productService.removeItemFromVirtualCart(productcode);
        List<ProductVirtualCartDTO> obj = productService.listAllCartItems();
        if (obj != null) {
            model.addAttribute("products", productService.listAllCartItems());
        }
        model.addAttribute("total", productService.totalCharges());
        return "productcart";
    }
    @PostMapping("/add")
    public String saveProduct(@ModelAttribute("product") Product product, BindingResult result, Model model) {
        //TODO exception handling if product already exists
        productService.saveProduct(product);
        return "redirect:/product/admin";
    }
    @GetMapping("/delete/{productcode}")
    public String deleteProduct(@PathVariable String productcode) {
        productService.deleteProduct(productcode);
        return "redirect:/product/admin";
    }
    @GetMapping("/add")
    public String addProduct(Model model) {
        Set<String> productcodes = new HashSet<>();
       // for (ProductLine pl : productLineService.findAllProductLine()) {
        //    productcodes.add(pl.getProductLine());

       // }
        model.addAttribute("product", new Product());
        model.addAttribute("productlines", productcodes);
        return "productadd";
    }
    @GetMapping("/edit/{productcode}")
    public ModelAndView editProduct(@PathVariable("productcode") String productcode) {
        ModelAndView editview = new ModelAndView("productadd");
        Set<String> productcodes = new HashSet<>();
        //for (ProductLine pl : productLineService.findAllProductLine()) {
        //    productcodes.add(pl.getProductLine());
       // }
        editview.addObject("productlines", productcodes);
        Product product = productService.findProductByProductCode(productcode);
        editview.addObject("product", product);
        return editview;
    }
    @GetMapping("/order")
    public String saveMyOrders(HttpServletRequest request) {
        productService.processMyOrders(request);
        return "redirect:/product/";
    }
    @GetMapping("/search/{productname}")
    public String searchProduct(@PathVariable("productname") String productname, Model model) {
        var x = productService.searchProductByName(productname);
        model.addAttribute("products", x);
        return "productlist";

    }
*/

}