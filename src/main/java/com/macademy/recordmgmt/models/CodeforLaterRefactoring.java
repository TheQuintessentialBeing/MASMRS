package com.macademy.recordmgmt.models;

public class CodeforLaterRefactoring {

    // Step 2: Process each student Header
//       /* Iterator<StudentRecordHeader> it = allHeaders.iterator();
//        while (it.hasNext()) {
//            System.out.println("Inside while loop" + it);
//            StudentRecordHeader a = new StudentRecordHeader();
//            a.setStudentId(it.next().getStudentId());
//            a.setFirstName(it.next().getFirstName());
//            a.setMiddleName(it.next().getMiddleName());
//            a.setLastName(it.next().getLastName());
//            a.setDateOfBirth(it.next().getDateOfBirth());
//            a.setGender(it.next().getGender());
//            a.setSection(it.next().getSection());
//            a.setAcademicYear(it.next().getAcademicYear());
//            a.setGrade(it.next().getGrade());
//            a.setSection(it.next().getSection());
//            a.setNumberOfSubjects(it.next().getNumberOfSubjects());
//            a.setQuarterOneSum(it.next().getQuarterOneSum());
//            a.setQuarterOneRank(it.next().getQuarterOneRank());
//            a.setQuarterTwoSum(it.next().getQuarterTwoSum());
//            a.setQuarterTwoRank(it.next().getQuarterTwoRank());
//            a.setSemesterOneRank(it.next().getSemesterOneRank());
//            a.setQuarterThreeSum(it.next().getQuarterThreeSum());
//            a.setQuarterThreeRank(it.next().getQuarterThreeRank());
//            a.setQuarterFourSum(it.next().getQuarterFourSum());
//            a.setQuarterFourRank(it.next().getQuarterFourRank());
//            a.setSemesterTwoRank(it.next().getSemesterTwoRank());
//            a.setAllSectionRank(it.next().getAllSectionRank());
//            a.setTotalNumberOfStudents(it.next().getTotalNumberOfStudents());
//            a.setAllSectionPercentile(it.next().getAllSectionPercentile());
//            a.setDetailrows(d);


    /// /////////////////////////




       /*for (Object[] r : headers) {

            int student_id = (Integer) r[0];                                                                // 0 s.student_id
            String first_name = (String) r[1];                                      // 1 s.first_name
            String middle_name = (String) r[2];                                     // 2 s.middle_name
            String last_name = (String) r[3];                                       // 3 s.last_name
            Date date_of_birth = (Date) r[4];                                                               // 4 s.date_of_birth
            //String gender = (r[5] instanceof Character) ? (Character) r[5] : ((String) r[5]).charAt(0);   // 5 s.gender
            Character gender = (r[5] instanceof Character) ? (Character) r[5] : ((String) r[5]).charAt(0);                                                                  // 5 s.gender
            String academic_year = (String) r[6];                                                           // 6 r.academic_year
            int grade = (Integer) r[7];                                                                    // 7 r.grade
            String section = (String) r[8];                                                                 // 8 r.section
            int NumberOfSubjects = ((Number) r[9]).intValue();                      // 9 count(*)      NumberOfSubjects,
            int Q1Sum = ((Number) r[10]).intValue();                                                        // 10 sum(r.q1)    Q1Sum
            int Q1Rank = ((Number) r[11]).intValue();                                                        // 11 RANK()       Q1Rank,
            double Q2Sum = ((Number) r[12]).doubleValue();                                                  // 12 sum( r.q2)   Q2Sum
            int Q2Rank = ((Number) r[13]).intValue();                                                       // 13 RANK()       Q2Rank
            int Sem1Rank = ((Number) r[14]).intValue();                                                    // 14 RANK() )     Sem1Rank
            double Q3Sum = ((Number) r[15]).doubleValue();                                                 // 15 sum(r.q3)    Q3Sum
            int Q3Rank = ((Number) r[16]).intValue();                                                      // 16 DENSE_RANK() Q3Rank
            double Q4Sum = ((Number) r[17]).doubleValue();                                                 // 17 sum( r.q4)   Q4Sum
            int Q4Rank = ((Number) r[18]).intValue();                                                      // 18 DENSE_RANK() Q4Rank
            int Sem2Rank = ((Number) r[19]).intValue();                                                    // 19 DENSE_RANK() Sem2Rank
            int OverAllRank = ((Number) r[20]).intValue();                                                // 20 DENSE_RANK() OverAllRank
            int TotalNumber = ((Number) r[21]).intValue();                                                 // 21 COUNT(*)     TotalNumber
            int Over25NTileRank = ((Number) r[22]).intValue();                                              // 22 NTILE()      Over25NTileRank
            byte[] photo =(r[23]!=null) ? null : null ;                                                     // 23 s.photo
            boolean is_active = ((Boolean) r[24]).booleanValue();                                           // 24 s.is_Active

        //    StudentRecordHeader d = new StudentRecordHeader(
                 //   student_id, first_name, middle_name, last_name, date_of_birth,
                   // gender, academic_year, grade, section, NumberOfSubjects, Q1Sum,
                   // Q1Rank, Q2Sum, Q2Rank, Sem1Rank, Q3Sum, Q3Rank, Q4Sum, Q4Rank
                  //  , Sem2Rank, OverAllRank, TotalNumber, Over25NTileRank, photo, is_active
                    //, studentRecordRepository.findAll()

          //  );
            List<StudentRecordHeader> studentRecordHeaders


        }


        return studentRecordHeaders;
    }


    //if (header.size() != 0) {
    //   System.out.println(" 1. studentId : " + intV + " and a/c year : " + stringV + " header_size : " + header.size());
    // what if the id is not in the student database
    // Step 1: Process each student Header
    // shall be casted
    //   StudentRecordHeader srho = new StudentRecordHeader();
    //   List<StudentRecord> k= studentRecordRepository
    //           .findAll()
    //           .stream()
    //           .filter(r -> r.getStudentId() == studentId
    //                   && r.getAcademicYear().equals(academicYear))
    //          .collect(Collectors.toList());
    //   System.out.println(" 2. ============================>>>>> in side details Size: " + d.size());
*/
}
