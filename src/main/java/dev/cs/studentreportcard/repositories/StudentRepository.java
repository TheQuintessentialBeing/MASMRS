package dev.cs.studentreportcard.repositories;

import dev.cs.studentreportcard.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

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
*/
    // student

    @Query(name="SqlSearchStudentByStudentId")
    Student findByStudentId(@Param ("studentId") Integer studentId);
}
