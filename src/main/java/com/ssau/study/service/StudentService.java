package com.ssau.study.service;

import com.ssau.study.dto.GroupPojo;
import com.ssau.study.dto.StudentPojo;
import com.ssau.study.jpa.GroupRepository;
import com.ssau.study.jpa.StudentRepository;
import com.ssau.study.orm.Group;
import com.ssau.study.orm.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public List<StudentPojo> findAll(String name) {
        List<StudentPojo> result = new ArrayList<>();
        for (Student student : name == null ? studentRepository.findAll() : studentRepository.findAllByNameContainingIgnoreCase(name)) {
            result.add(StudentPojo.fromEntity(student));
        }
        return result;
    }
    public StudentPojo add(StudentPojo studentPojo) {
        Student student = StudentPojo.toEntity(studentPojo);
        return StudentPojo.fromEntity(studentRepository.save(student));
    }


}
