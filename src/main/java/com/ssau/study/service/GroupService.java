package com.ssau.study.service;

import com.ssau.study.dto.GroupPojo;
import com.ssau.study.dto.StudentPojo;
import com.ssau.study.jpa.GroupRepository;
import com.ssau.study.jpa.StudentRepository;
import com.ssau.study.orm.Group;
import com.ssau.study.orm.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    private final StudentRepository studentRepository;
    public long count(){
        return groupRepository.count();
    }

    public List<GroupPojo> findAll(String name) {
        List<GroupPojo> result = new ArrayList<>();
        for (Group group : name == null ? groupRepository.findAll() : groupRepository.findAllByNameContainingIgnoreCase(name)) {
            result.add(GroupPojo.fromEntity(group));
        }
        return result;
    }
    public List<StudentPojo> findAllStudents(long groupId){
        List<StudentPojo> studentsPojo = new ArrayList<>();
        for (Student student : studentRepository.findAllByGroupId(groupId)){
            studentsPojo.add(StudentPojo.fromEntity(student));
        }
        return studentsPojo;
    }
    public GroupPojo update(GroupPojo groupPojo){
        Group group = groupRepository.findById(groupPojo.getId()).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return GroupPojo.fromEntity(groupRepository.save(group));
    }
    public GroupPojo findById(long id){
        Group group = groupRepository.findById(id).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return GroupPojo.fromEntity(group);
    }
    public StudentPojo createStudent(long groupId, StudentPojo studentPojo){
        Student student = StudentPojo.toEntity(studentPojo);
        student.setGroup(groupRepository.findById(groupId).orElseThrow());
        return StudentPojo.fromEntity(studentRepository.save(student));
    }
    public GroupPojo moveStudent(long groupId, long studentId){
        Student student = studentRepository.findById(studentId).orElseThrow();
        Group group = groupRepository.findById(groupId).orElseThrow();
        student.setGroup(group);
        studentRepository.save(student);
        return GroupPojo.fromEntity(group);
    }
    public GroupPojo create(GroupPojo groupPojo){
        Group group = GroupPojo.toEntity(groupPojo);
        return GroupPojo.fromEntity(groupRepository.save(group));
    }
    public void delete(long id){
        if(groupRepository.existsById(id)){
            groupRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}