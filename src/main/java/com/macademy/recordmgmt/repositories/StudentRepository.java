package com.macademy.recordmgmt.repositories;

import com.macademy.recordmgmt.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

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
*/
    // student

    @Query(name = "SqlSearchStudentByStudentId")
    Student findByStudentId(@Param("studentId") Integer studentId);


    @Query(value = """
            SELECT * FROM Students
            WHERE first_name LIKE CONCAT('%', :searchTerm, '%')
            OR  middle_name LIKE CONCAT('%', :searchTerm, '%')
            OR  last_name LIKE CONCAT('%', :searchTerm, '%')
            OR  student_id = :searchTerm
            """, nativeQuery = true)
    List<Student> findByStudentIdOrNameContainingIgnoreCase(@Param("searchTerm") String searchTerm);

}
