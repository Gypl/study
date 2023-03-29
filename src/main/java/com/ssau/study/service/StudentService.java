package com.ssau.study.service;

import com.ssau.study.dto.StudentPojo;
import com.ssau.study.jpa.StudentRepository;
import com.ssau.study.orm.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public long count(){
        return studentRepository.count();
    }
    public List<StudentPojo> findAll(String name) {
        List<StudentPojo> result = new ArrayList<>();
        for (Student student : name == null ? studentRepository.findAll()
                : studentRepository.findAllByNameContainingIgnoreCase(name)) {
            result.add(StudentPojo.fromEntity(student));
        }
        return result;
    }
    public StudentPojo create(StudentPojo studentPojo) {
        Student student = StudentPojo.toEntity(studentPojo);
        return StudentPojo.fromEntity(studentRepository.save(student));
    }
    public StudentPojo update(StudentPojo studentPojo){
        Student student = StudentPojo.toEntity(studentPojo);
        return StudentPojo.fromEntity(studentRepository.save(student));

    }
    public StudentPojo findById(long id){
        return StudentPojo.fromEntity(studentRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
    public void delete(long id) { studentRepository.deleteById(id);}
}
