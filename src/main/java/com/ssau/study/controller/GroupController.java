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

    @GetMapping
    public List<GroupPojo> findAll() {
        return groupService.findAll(null);
    }

    @GetMapping("/{name}")
    public List<GroupPojo> findAllByName(@PathVariable String name) {
        return groupService.findAll(name);
    }

    @PostMapping("/{groupId}/students")
    public StudentPojo createStudent(@PathVariable long groupId, @RequestBody StudentPojo pojo) {
        return groupService.create(groupId, pojo);
    }
}
