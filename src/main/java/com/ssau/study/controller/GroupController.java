package com.ssau.study.controller;

import com.ssau.study.annotations.OnlyAdmin;
import com.ssau.study.dto.GroupPojo;
import com.ssau.study.dto.StudentPojo;
import com.ssau.study.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;
    @GetMapping("/count")
    public long count() {
        return groupService.count();
    }

    @GetMapping
    public List<GroupPojo> findAll() {
        return groupService.findAll(null);
    }

    @GetMapping("/{name}")
    public List<GroupPojo> findAllByName(@PathVariable String name) {
        return groupService.findAll(name);
    }
    @GetMapping("/id/{id}")
    public GroupPojo findAllByName(@PathVariable long id){
        return groupService.findById(id);
    }

    @PostMapping("/create")
    public GroupPojo create(@RequestBody GroupPojo groupPojo){
        return groupService.create(groupPojo);
    }
    @OnlyAdmin
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        groupService.delete(id);
    }
    @GetMapping("/{groupId}/students")
    public List<StudentPojo> findStudents(@PathVariable long groupId){
        return  groupService.findAllStudents(groupId);
    }
    @OnlyAdmin
    @PostMapping("/{groupId}/students")
    public StudentPojo createStudent(@PathVariable long groupId, @RequestBody StudentPojo pojo) throws ParseException {
        return groupService.createStudent(groupId, pojo);
    }
    @OnlyAdmin
    @PutMapping("/update/{id}")
    public GroupPojo update(@RequestBody GroupPojo groupPojo, @PathVariable long id) {
        return groupService.update(groupPojo);
    }

    @PostMapping("/{groupId}/students/{studentId}/move")
    public GroupPojo moveStudent(@PathVariable long groupId, @PathVariable long studentId){
        return groupService.moveStudent(groupId,studentId);
    }
}
