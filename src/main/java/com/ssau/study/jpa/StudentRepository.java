package com.ssau.study.jpa;

import com.ssau.study.orm.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends
        JpaRepository<Student, Long> {
    @Query(value = "select * from public.students where name ilike '%' || :name || '%'", nativeQuery = true)
    List<Student> selectByName(String name);
}
