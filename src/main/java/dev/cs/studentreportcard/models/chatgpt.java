package dev.cs.studentreportcard.models;

import dev.cs.studentreportcard.DTO.StudentRecordHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class chatgpt {

/*
-- Database Script
CREATE TABLE parents (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL
);

CREATE TABLE children (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        parent_id BIGINT,
        name VARCHAR(50) NOT NULL,
age INT,
FOREIGN KEY (parent_id) REFERENCES parents(id)
        );

INSERT INTO parents (name) VALUES ('John'), ('Emma');
INSERT INTO children (parent_id, name, age) VALUES
        John ->(1, 'Alice', 10),
        John ->(1, 'Bob', 12),
        Emma ->(1, 'Charlie', 11),
        Emma ->(2, 'David', 13),
        Emma ->(2, 'Eva', 12);

// Lombok Entities
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Parent {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> children = new ArrayList<>();
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Child {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
}

// Spring Controllers
@RestController
@RequestMapping("/api/parents")
class ParentRestController {

    private final ParentRepository parentRepository;

    public ParentRestController(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @GetMapping
    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    @PostMapping
    public Parent createParent(@RequestBody Parent parent) {
        return parentRepository.save(parent);
    }
}

@Controller
@RequestMapping("/report")
class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/pdf")
    public ResponseEntity<Resource> getPdfReport() {
        reportService.generateAdvancedParentChildReport();
        File file = new File("ParentChildReport.pdf");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ParentChildReport.pdf");
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).body(new FileSystemResource(file));
    }
}
/*
-- Database Script
CREATE TABLE parents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE children (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parent_id BIGINT,
    name VARCHAR(50) NOT NULL,
    age INT,
    FOREIGN KEY (parent_id) REFERENCES parents(id)
);

INSERT INTO parents (name) VALUES ('John'), ('Emma');
INSERT INTO children (parent_id, name, age) VALUES
(1, 'Alice', 10),
(1, 'Bob', 12),
(1, 'Charlie', 11),
(2, 'David', 13),
(2, 'Eva', 12);

// Lombok Entities
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Parent {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> children = new ArrayList<>();
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Child {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
}

// Spring Controllers
@RestController
@RequestMapping("/api/parents")
class ParentRestController {

    private final ParentRepository parentRepository;

    public ParentRestController(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @GetMapping
    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    @PostMapping
    public Parent createParent(@RequestBody Parent parent) {
        return parentRepository.save(parent);
    }
}

@Controller
@RequestMapping("/report")
class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/pdf")
    public ResponseEntity<Resource> getPdfReport() {
        reportService.generateAdvancedParentChildReport();
        File file = new File("ParentChildReport.pdf");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ParentChildReport.pdf");
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).body(new FileSystemResource(file));
    }
}

// PDF Generation Service
@Service
class ReportService {

    public void generateAdvancedParentChildReport() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("ParentChildReport.pdf"));
            document.open();

            // Adding title
            document.add(new Paragraph("Parent-Child Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));

            // Fetching data
            List<Parent> parents = fetchParentsWithChildren();

            for (Parent parent : parents) {
                document.add(new Paragraph("Parent: " + parent.getName()));
                document.add(new Paragraph("Total Children: " + parent.getChildren().size()));
                document.add(new Paragraph("Children Details:"));

                PdfPTable table = new PdfPTable(2);
                table.addCell("Name");
                table.addCell("Age");

                for (Child child : parent.getChildren()) {
                    table.addCell(child.getName());
                    table.addCell(String.valueOf(child.getAge()));
                }

                document.add(table);
                document.add(new Paragraph("\n"));
            }

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Parent> fetchParentsWithChildren() {
        // Simulate database fetch
        return List.of(
                new Parent(1L, "John", List.of(
                        new Child(1L, "Alice", 10, null),
                        new Child(2L, "Bob", 12, null)
                )),
                new Parent(2L, "Emma", List.of(
                        new Child(3L, "David", 13, null),
                        new Child(4L, "Eva", 12, null)
                ))
        );
    }
}

* */

/*
* Always show details

# Define the content for the complete integration document with CRUD operations, Thymeleaf, Postman, and more

full_integration_content = """
-- Database Script
CREATE TABLE parents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE children (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parent_id BIGINT,
    name VARCHAR(50) NOT NULL,
    age INT,
    FOREIGN KEY (parent_id) REFERENCES parents(id)
);

INSERT INTO parents (name) VALUES ('John'), ('Emma');
INSERT INTO children (parent_id, name, age) VALUES
(1, 'Alice', 10),
(1, 'Bob', 12),
(1, 'Charlie', 11),
(2, 'David', 13),
(2, 'Eva', 12);

// Lombok Entities
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Parent {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> children = new ArrayList<>();
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Child {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
}

// Spring Controllers and Services
@RestController
@RequestMapping("/api/parents")
class ParentRestController {

    private final ParentRepository parentRepository;

    public ParentRestController(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @GetMapping
    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    @PostMapping
    public Parent createParent(@RequestBody Parent parent) {
        return parentRepository.save(parent);
    }
}

@Controller
@RequestMapping("/report")
class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/pdf")
    public ResponseEntity<Resource> getPdfReport() {
        reportService.generateAdvancedParentChildReport();
        File file = new File("ParentChildReport.pdf");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ParentChildReport.pdf");
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).body(new FileSystemResource(file));
    }
}

// PDF Generation Service
@Service
class ReportService {

    public void generateAdvancedParentChildReport() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("ParentChildReport.pdf"));
            document.open();

            // Adding title
            document.add(new Paragraph("Parent-Child Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));

            // Fetching data
            List<Parent> parents = fetchParentsWithChildren();

            for (Parent parent : parents) {
                document.add(new Paragraph("Parent: " + parent.getName()));
                document.add(new Paragraph("Total Children: " + parent.getChildren().size()));
                document.add(new Paragraph("Children Details:"));

                PdfPTable table = new PdfPTable(2);
                table.addCell("Name");
                table.addCell("Age");

                for (Child child : parent.getChildren()) {
                    table.addCell(child.getName());
                    table.addCell(String.valueOf(child.getAge()));
                }

                document.add(table);
                document.add(new Paragraph("\n"));
            }

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Parent> fetchParentsWithChildren() {
        // Simulate database fetch
        return List.of(
                new Parent(1L, "John", List.of(
                        new Child(1L, "Alice", 10, null),
                        new Child(2L, "Bob", 12, null)
                )),
                new Parent(2L, "Emma", List.of(
                        new Child(3L, "David", 13, null),
                        new Child(4L, "Eva", 12, null)
                ))
        );
    }
}

// Thymeleaf HTML for Display
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Parent-Child Report</title>
</head>
<body>
<h1>Parent-Child Report</h1>
<table>
    <thead>
        <tr>
            <th>Parent Name</th>
            <th>Child Name</th>
            <th>Age</th>
        </tr>
    </thead>
    <tbody>
        <tr th:each="parent : ${parents}">
            <td th:text="${parent.name}"></td>
            <td th:each="child : ${parent.children}" th:text="${child.name}"></td>
            <td th:each="child : ${parent.children}" th:text="${child.age}"></td>
        </tr>
    </tbody>
</table>
</body>
</html>

// Postman Testing for CRUD
POST /api/parents
{
    "name": "Michael"
}

GET /api/parents

POST /api/parents/{parentId}/children
{
    "name": "James",
    "age": 5
}

GET /api/parents/{parentId}/children

-- End of Content
"""

# Create instance of FPDF class for the complete integration PDF
pdf = FPDF()
pdf.add_page()
pdf.set_auto_page_break(auto=True, margin=15)
pdf.set_font("Arial", size=12)

# Add the complete content to the PDF
for line in full_integration_content.split("\n"):
    pdf.multi_cell(0, 10, line)

# Save the complete PDF
full_integration_pdf_path = "/mnt/data/CompleteIntegration_ParentChild_Report.pdf"
pdf.output(full_integration_pdf_path)

full_integration_pdf_path*/
    /*

     /*Logic
         * 1. get studnet Id from student
         */
       /* Integer searchStudentId = studentRepository
                .findAll()
                .stream()
                .map(Student::getStudentId)
                .filter(studentId -> Objects.equals(studentId, studentid)).mapToInt()*/

    /* 2. use the studet id in (step 1) to search the records associated in records*/
        /*TODO
        List<StudentRecord> list =  studentRecordRepository.findAll();
        System.out.println("Services ssssssssssssssssssssssssssssssssss:" + list);
        List<StudentRecord> finalList = list
                    .stream()
                    .filter(r -> r.getStudent().getStudentId().equals(studentid)) // check it again
                    .collect(Collectors.toList());
        return finalList;

         }*/

    // new method
/*
    public List<StudentRecordHeader> getStudentRecordReport() {
        List<StudentRecordHeader> studentStatistics = new ArrayList<>(); // from view

        // Step 1: Fetch all records
        List<StudentRecord> d = studentRecordRepository.findAll(); // details data

        // Step 2: Process each student Header
        for (StudentRecordHeader h : studentStatistics) {

            StudentRecordHeader rpt = new StudentRecordHeader();

            rpt.setStudentId(h.getStudentId());
            rpt.setFirstName(h.getFirstName());
            rpt.setLastName(h.getLastName());
            rpt.setAcademicYear(h.getAcademicYear());
            rpt.setGrade(h.getGrade());
            rpt.setSection(h.getSection());

            // Step 3:
            List<StudentRecord> dtl = d.stream()
                    .filter(rcrd -> rcrd.getRecordId() == h.getStudentId()
                    ).collect(Collectors.toList());

            h.setDetailStudentRows(dtl);

            studentStatistics.add(rpt);
        }

        return studentStatistics;
    }

*/




    /*public Page<StudentRecord> searchStudentByStudentId(Integer studentid) {
        Pageable p = PageRequest.of(0, 5);
        List<StudentRecord> finalList = new ArrayList<>();
        List<StudentRecord> list = studentRecordRepository.findAll();
        finalList = list
                .stream()
                .filter(rr -> rr.getStudentId().equals(studentid))
                .collect(Collectors.toList());
        final int start = (int) p.getOffset();
        final int end = Math.min((start + p.getPageSize()), finalList.size());
        final PageImpl<StudentRecord> page = new PageImpl<>(finalList.subList(start, end), p, finalList.size());
        return page;

    }*/



    /*
    //CRUD
    public Page<Student> listAllStudents(PageRequest pageRequest) {
        return studentRepository.findAll(pageRequest);
    }

    public Student getStudentByStudentId(@Param("studentid") int studentId) {
        return studentRepository.findByStudentId(studentId);
    }

   public void saveStudent(Student student) {
        studentRepository.save(student);
    }
    // TODO explored
    public void updateStudent(Integer studentId, Student student) {
        studentRepository.save(student);
    }


    public void deleteStudent(Integer studentId) {
        studentRepository.delete(findByStudentId(studentId));
    }

    private Student findByStudentId(Integer studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public int numberOfStudentsInSchool() {

        int size = studentRepository.findAll().size();

        return size;


    }

    public Map<Integer,Integer> totalStudentsByYearAndGrade() {
        final List<Student> all = studentRepository.findAll();
        Map<Integer, Integer> map  = new HashMap<>();
        //TODO we need to count students by year, section
        // eg. 2017     G10.   50
        //.    2017.    G9.    45
        //.    2016.    G9.    30
        //.    2017.    G10.   20
        return map;

    }
    public Map<Integer,Integer>  topNStudentsCurrentYearByGrade(Integer numberofstudents) {


        Map<Integer, Integer> map  = new HashMap<>();
        return map;
    }

    public Map<Integer,Integer>  topNStudentsCurrentYearByGradeByGender(Integer numberofstudents, String gender) {

        Map<Integer, Integer> map  = new HashMap<>();
        return map;
    }

    public Student topNAllTimeScorerBySubject(String Subject) {
       //TODO
       // we have to look up the max result for all subjects for all years in all quarters .

        return new Student();
    }

   public Page<Student> searchStudentByStudentId(String firstname) {
        String firstnameToUpper = firstname.toUpperCase();
        Pageable p = PageRequest.of(0, 5);
        List<Student> finalList = new ArrayList<>();
        List<Student> list = studentRepository.findAll();
        if (firstname != "")
            finalList = list
                    .stream()
                    .filter(pp -> pp.getFirstName().toUpperCase()
                            .contains(firstnameToUpper))
                    .collect(Collectors.toList());
        else
            finalList = list;
        final int start = (int) p.getOffset();
        final int end = Math.min((start + p.getPageSize()), finalList.size());
        final Page<Student> page = new PageImpl<>(finalList.subList(start, end), p, finalList.size());
        return page;

 }
*/


}






