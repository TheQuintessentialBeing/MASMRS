package dev.cs.studentreportcard.repositories;
import dev.cs.studentreportcard.DTO.StudentRecordHeader;
import dev.cs.studentreportcard.models.StudentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface StudentRecordRepository extends JpaRepository<StudentRecord,Integer> {
    // stat that is all sections
    // Note : No spaces are allowed in the query , also no space in the where close right side after : and the variable in this case i
    @Query(value = """
            SELECT
             s.student_id
            ,s.first_name
            ,s.middle_name
            ,s.last_name
            ,s.date_of_birth
            ,s.gender
            ,r.academic_year
            ,r.grade
            ,r.section
            
            ,count(*) NumberOfSubjects
            
            ,sum(r.q1) Q1Sum
            ,RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q1 desc ) Q1Rank
            
            ,sum( r.q2) as Q2Sum
            ,RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q2 desc ) Q2Rank
            ,RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1 + q2) desc ) Sem1Rank
            
            ,sum(r.q3) Q3Sum
            ,DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q3 desc ) Q3Rank
            
            ,sum( r.q4) as Q4Sum
            ,DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q4 desc ) Q4Rank
            ,DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1+ q2 +q3 + q4) desc ) Sem2Rank
            
            ,DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) OverAllRank
            ,COUNT(*)     OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) TotalNumber
            ,NTILE(4)     OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) Over25NTileRank
            
            ,s.photo
            ,s.is_Active
            FROM students s
            INNER JOIN student_records r on s.student_id = r.student_id
            WHERE s.student_id = :i
            AND r.academic_year =:a
            GROUP BY r.academic_year ,r.grade ,r.section""" , nativeQuery=true)
    List<Object[]> findRecordsByYearGradeAndSection(@Param("i") Integer i, @Param("a") String a );

   @Query(value = """
            SELECT *
            FROM student_records r
            Where r.student_id = :student_id
            AND r.academic_year = :academic_year""" , nativeQuery = true)
    List<StudentRecord> findStudentRecordByIdAndAcademicYear(@PathVariable("student_id") Integer student_id,
                                                             @PathVariable("academic_year") String academic_year);


}
