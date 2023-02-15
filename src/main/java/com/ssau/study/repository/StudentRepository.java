package com.ssau.study.repository;

import com.ssau.study.entity.Student;

import java.util.List;

public interface StudentRepository {
    int count();

    List<Student> findAll();

    List<Student> findAllByName(String name);

    void update();

    Student addStudent();

    int deleteById(String id);
}
