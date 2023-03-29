package com.ssau.study.controller;


import com.ssau.study.dto.StudentPojo;
import com.ssau.study.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/count")
    public long count() {
        return studentService.count();
    }

    @GetMapping
    public List<StudentPojo> findAll() {
        return studentService.findAll(null);
    }

    @GetMapping("/{name}")
    public List<StudentPojo> findAllByName(@PathVariable String name) {
        return studentService.findAll(name);
    }

    @GetMapping("/id/{id}")
    public StudentPojo findById(@RequestBody long id) {
        return studentService.findById(id);
    }
    @GetMapping("/create")
    public StudentPojo createStudent(@RequestBody StudentPojo studentPojo) {
        return studentService.create(studentPojo);
    }

    @PutMapping("/update")
    public StudentPojo updateStudent(@RequestBody StudentPojo studentPojo) {
        return studentService.update(studentPojo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable(name = "id") Long id) {
        studentService.delete(id);
    }
}
