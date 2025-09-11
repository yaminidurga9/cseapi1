package com.example.CSEWEBSQL4;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepo extends JpaRepository<CseStudent, Integer> {
@Query("SELECT s FROM CseStudent s JOIN s.subjects sub WHERE sub.subname= :sub")
List<CseStudent> FindBySubjectsSubName(@Param("sub") String sub);
}
