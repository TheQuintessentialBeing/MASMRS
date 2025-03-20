package dev.cs.studentreportcard.services;

import dev.cs.studentreportcard.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public StudentService(){}
    public void doSomething() {
        System.out.println("Service is working!");
    }
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

