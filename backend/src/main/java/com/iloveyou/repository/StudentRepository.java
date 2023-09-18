package com.iloveyou.repository;
import org.springframework.data.repository.CrudRepository;
import com.iloveyou.entity.StudentEntity;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Long>{
     List<StudentEntity> findBystudentId(String studentId);
}
