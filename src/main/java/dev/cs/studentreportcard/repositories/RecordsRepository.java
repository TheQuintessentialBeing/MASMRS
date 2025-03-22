package dev.cs.studentreportcard.repositories;

import dev.cs.studentreportcard.models.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface RecordsRepository extends JpaRepository<Records,Integer> {

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
    @Query(name="SqlSearchStudentByStudentId")
    Records findByRecordsId(@Param("studentId") Integer studentId);

}

