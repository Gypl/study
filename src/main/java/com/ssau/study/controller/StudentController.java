package com.ssau.study.controller;

import com.ssau.study.entity.Student;
import com.ssau.study.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/count")
    public int count() {
        return studentRepository.count();
    }

    @GetMapping
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/{name}")
    public List<Student> findAllByName(@PathVariable String name) {
        return studentRepository.findAllByName(name);
    }

    @PostMapping("/create")
    public Student saveStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/update")
    public int updateStudent(@RequestBody Student student) {
        return studentRepository.update(student);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteStudent(@PathVariable(name = "id") Long id) {
        return studentRepository.delete(id);
    }
}
