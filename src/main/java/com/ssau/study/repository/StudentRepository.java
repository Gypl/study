package com.ssau.study.repository;

import com.ssau.study.entity.Student;

import java.util.List;

public interface StudentRepository {
    int count();

    List<Student> findAll();

    List<Student> findAllByName(String name);

    int update(Student student);

    Student save(Student student);

    int delete(long id);
}
