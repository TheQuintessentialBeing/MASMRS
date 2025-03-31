package dev.cs.studentreportcard.repositories;

import dev.cs.studentreportcard.models.StudentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRecordRepository extends JpaRepository<StudentRecord,Integer> {
       // stat that is all sections
        @Query("SELECT r FROM StudentRecord r JOIN r.student s WHERE r.academicYear = :year AND r.grade = :grade AND r.section = :section")
        List<StudentRecord> findRecordsByYearGradeAndSection(String year, String grade, String section);
        // student record for all years , grades, sections

        @Query("SELECT r FROM StudentRecord r WHERE r.student.studentId = :studentId")
        List<StudentRecord> findByStudentId(Long studentId);

    //Student findByProductCode();


    /*@Query(name = "sqlSearchProductByProductCode")
    Product findByProductCode(@Param("productcode") String productCode);
    @Modifying
    @Transactional
    @Query("UPDATE Product p set p.quantityInStock= p.quantityInStock - 1  Where p.productCode=:productcode")
    void decreaseStockQuantity(@Param("productcode") String productcode);
    @Modifying
    @Transactional
    @Query("UPDATE Product p set p.quantityInStock= p.quantityInStock + 1 Where p.productCode=:productcode")
    void increaseStockQuantity(@Param("productcode") String productcode);
    @Modifying
    @Transactional
    @Query("UPDATE Product p set p.quantityInStock= p.quantityInStock +:quantityInStock Where p.productCode=:productcode")
    void increaseStockQuantityBatch(@Param("quantityInStock") short quantityInStock, @Param("productcode") String productcode);

    // student
*/
  //  @Query(name="SqlSearchStudentByStudentId")
  //  StudentRecord findByRecordsId(@Param("studentId") Integer studentId);

}



