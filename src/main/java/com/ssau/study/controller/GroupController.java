package com.ssau.study.controller;

import com.ssau.study.dto.GroupPojo;
import com.ssau.study.dto.StudentPojo;
import com.ssau.study.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;
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

    @PostMapping("/add")
    public GroupPojo add(@RequestBody GroupPojo groupPojo){
        return groupService.add(groupPojo);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody long id){
        groupService.delete(id);
    }
    @GetMapping("/{groupId}/students")
    public List<StudentPojo> findStudents(@PathVariable long groupId){
        return  groupService.findAllStudents(groupId);
    }
    @PostMapping("/{groupId}/students")
    public StudentPojo createStudent(@PathVariable long groupId, @RequestBody StudentPojo pojo) {
        return groupService.createStudent(groupId, pojo);
    }
    @PostMapping("/{groupId}/students/{studentId}/move")
    public GroupPojo moveStudent(@PathVariable long groupId, @PathVariable long studentId){
        return groupService.moveStudent(groupId,studentId);
    }
}
