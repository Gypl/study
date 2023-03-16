package com.ssau.study.jpa;

import com.ssau.study.orm.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends
        JpaRepository<Student, Long> {
    List<Student> findAllByNameContainingIgnoreCase(String name);
    List<Student> findAllByGroupId(long groupId);
}
