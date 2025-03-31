package dev.cs.studentreportcard.repositories;

import dev.cs.studentreportcard.DTO.StudentRecordHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRecordHeaderRepository extends JpaRepository<StudentRecordHeader, Integer> {

    // using the DTO class in Repository and map the data from the view in the db and return in the method
    // @Query("SELECT  new dev.cs.studentreportcard.DTO.StudentRecordHeader() from viewName v")
    // public List<StudentRecordHeader> findAllHeaders();
    // second option is to make the DTO object inmmutable class and use it in the repository like normal entitry class
     List<StudentRecordHeader> findAll();
}
