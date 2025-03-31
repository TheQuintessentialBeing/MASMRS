package dev.cs.studentreportcard.services;

import dev.cs.studentreportcard.DTO.StudentRecordHeader;
import dev.cs.studentreportcard.models.StudentRecord;
import dev.cs.studentreportcard.repositories.StudentRecordHeaderRepository;
import dev.cs.studentreportcard.repositories.StudentRecordRepository;
import dev.cs.studentreportcard.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentRecordService {
    @Autowired
    StudentRecordRepository studentRecordRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentRecordHeaderRepository studentRecordHeaderRepository;

    public StudentRecordService(StudentRecordRepository studentRecordRepository, StudentRepository studentRepository) {
        this.studentRecordRepository = studentRecordRepository;
        this.studentRepository = studentRepository;
    }

    public StudentRecordService() {
    }

    public Page<StudentRecord> listAllRecords(PageRequest pageRequest) {

        return studentRecordRepository.findAll(pageRequest);
    }

    public List<StudentRecordHeader> getStudentRecordReport() {
        List<StudentRecordHeader> allHeaders = studentRecordHeaderRepository.findAll(); // from view
        System.out.println("Service->size of student header  :- "+ allHeaders.size());
        // Step 1: Fetch all records
        List<StudentRecord> d = studentRecordRepository.findAll(); // details data

        // Step 2: Process each student Header
        for (StudentRecordHeader headerRows : allHeaders) {

            StudentRecordHeader singleStudentReport = new StudentRecordHeader();

            singleStudentReport.setStudentId(headerRows.getStudentId());
           singleStudentReport.setFirstName(headerRows.getFirstName());
            singleStudentReport.setLastName(headerRows.getLastName());
            singleStudentReport.setAcademicYear(headerRows.getAcademicYear());
            singleStudentReport.setGrade(headerRows.getGrade());
            singleStudentReport.setSection(headerRows.getSection());

            // Step 3:
            List<StudentRecord> dtl = d.stream()
                    .filter(rcrd -> rcrd.getRecordId() == headerRows.getStudentId()
                    ).collect(Collectors.toList());

            headerRows.setDetailStudentRows(dtl);

            allHeaders.add(singleStudentReport);
        }

        return allHeaders;
    }

}

