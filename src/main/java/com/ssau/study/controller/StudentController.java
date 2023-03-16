package com.ssau.study.controller;


import com.ssau.study.dto.StudentPojo;
import com.ssau.study.orm.Student;
import com.ssau.study.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

//    @GetMapping("/count")
//    public int count() {
//        return studentService.count();
//    }
//
//    @GetMapping
//    public List<Student> findAll() {
//        return studentService.findAll();
//    }
//
//    @GetMapping("/{name}")
//    public List<Student> findAllByName(@PathVariable String name) {
//        return studentService.findAllByName(name);
//    }
//
//    @PostMapping("/create")
//    public Student saveStudent(@RequestBody Student student) {
//        return studentService.create(student);
//    }
//
//    @PutMapping("/update")
//    public int updateStudent(@RequestBody Student student) {
//        return studentService.update(student);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public int deleteStudent(@PathVariable(name = "id") Long id) {
//        return studentService.delete(id);
//    }
}
