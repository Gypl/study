package com.ssau.study.controller;

import com.ssau.study.dto.StudentPojo;
import com.ssau.study.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/count")
    //@Secured("ROLE_ADMIN")
    public long count() {
        return studentService.count();
    }

    @GetMapping
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<StudentPojo> findAll() {
        return studentService.findAll(null);
    }

    @GetMapping("/name/{name}")
    public List<StudentPojo> findAllByName(@PathVariable String name) {
        return studentService.findAll(name);
    }

    @GetMapping("/id/{id}")
    public StudentPojo findById(@PathVariable long id) {
        return studentService.findById(id);
    }
    @PostMapping("/create")
    public StudentPojo createStudent(@RequestBody StudentPojo studentPojo) {
        return studentService.create(studentPojo);
    }
    @PutMapping("/update/{id}")
    public StudentPojo updateStudent(@RequestBody StudentPojo studentPojo, @PathVariable long id) {
        return studentService.update(studentPojo, id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable(name = "id") Long id) {
        studentService.delete(id);
    }
}
