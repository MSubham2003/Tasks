package com.subham.studentrecord.service;

import com.subham.studentrecord.entity.Student;
import com.subham.studentrecord.entity.dto.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto toDTO(Student entity) {
        return new StudentDto(entity.getName(), entity.getEmail(), entity.getPhone(), entity.getDepartment());
    }

    public Student toEntity(StudentDto dto) {
        Student entity = new Student();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setDepartment(dto.getDepartment());
        return entity;
    }
}