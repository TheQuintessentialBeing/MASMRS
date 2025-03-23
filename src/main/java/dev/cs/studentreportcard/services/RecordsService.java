package dev.cs.studentreportcard.services;
import dev.cs.studentreportcard.DTO.StudentStatDTO;
import dev.cs.studentreportcard.DTO.SubjectRecordDTO;
import dev.cs.studentreportcard.models.Records;
import dev.cs.studentreportcard.repositories.RecordsRepository;
import dev.cs.studentreportcard.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class RecordsService {
    @Autowired
    RecordsRepository recordsRepository;
    StudentRepository studentRepository;
    public RecordsService(RecordsRepository recordsRepository, StudentRepository studentRepository) {
        this.recordsRepository = recordsRepository;
        this.studentRepository = studentRepository;
    }
    public RecordsService(){}

   public Page<Records> listAllRecords(PageRequest pageRequest) {

        return recordsRepository.findAll(pageRequest);
    }

    public List<Records> searchRecordsByStudentId(Integer studentid) {
        List<Records> list = new ArrayList<>();
         list = recordsRepository.findAll();

        System.out.println("Services ssssssssssssssssssssssssssssssssss:" + list);
        List<Records> finalList = list
                    .stream()
                    .filter(r -> r.getStudent().getStudentId().equals(studentid)) // check it again
                    .collect(Collectors.toList());
        return finalList;

    }

    // new method

        public List<StudentStatDTO> getStudentStatisticsWithRank() {
            List<StudentStatDTO> studentStatistics = new ArrayList<>();

            // Step 1: Fetch all records
            List<Records> records = recordsRepository.findAll();

            // Step 2: Process each student
            for (Records record : records) {
                StudentStatDTO dto = new StudentStatDTO();
                // Set student details
                dto.setStudentId(record.getStudent().getStudentId());

                dto.setFirstName(record.getStudent().getFirstName());
                dto.setLastName(record.getStudent().getLastName());
                dto.setAcademicYear(record.getAcademicYear());
                dto.setGrade(record.getGrade());
                dto.setSection(record.getSection());

                // Step 3: Calculate semester averages and sums
                List<SubjectRecordDTO> subjectRecords = new ArrayList<>();
                Double semester1Sum = 0.0;
                Double semester2Sum = 0.0;
                for (Records r : records) {
                    SubjectRecordDTO subjectRecord = new SubjectRecordDTO();
                    subjectRecord.setSubject(r.getSubject());
                    subjectRecord.setQ1(r.getQ1());
                    subjectRecord.setQ2(r.getQ2());
                    subjectRecord.setSemesterIAverage((r.getQ1() + r.getQ2()) / 2);
                    subjectRecord.setQ3(r.getQ3());
                    subjectRecord.setQ4(r.getQ4());
                    subjectRecord.setSemesterIIAverage((r.getQ3() + r.getQ4()) / 2);

                    // Calculate total sums for each semester
                    semester1Sum += subjectRecord.getSemesterIAverage();
                    semester2Sum += subjectRecord.getSemesterIIAverage();

                    subjectRecords.add(subjectRecord);
                }

                dto.setSemester1Sum(semester1Sum);
                dto.setSemester2Sum(semester2Sum);
                dto.setTotalSum(semester1Sum + semester2Sum);
                dto.setSubjectRecords(subjectRecords);

                // Step 4: Calculate rank (this can be done by sorting or using a custom ranking system)
                // For simplicity, we are assigning rank 1 to all students
                dto.setRank(1); // This can be implemented based on your business logic for ranking

                studentStatistics.add(dto);
            }

            return studentStatistics;
        }






    /*public Page<Records> searchStudentByStudentId(Integer studentid) {
        Pageable p = PageRequest.of(0, 5);
        List<Records> finalList = new ArrayList<>();
        List<Records> list = recordsRepository.findAll();
        finalList = list
                .stream()
                .filter(rr -> rr.getStudentId().equals(studentid))
                .collect(Collectors.toList());
        final int start = (int) p.getOffset();
        final int end = Math.min((start + p.getPageSize()), finalList.size());
        final PageImpl<Records> page = new PageImpl<>(finalList.subList(start, end), p, finalList.size());
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


