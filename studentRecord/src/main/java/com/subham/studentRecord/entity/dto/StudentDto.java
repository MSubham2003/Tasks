package com.subham.studentRecord.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto {
    private String name;

    private String email;

    private String phone;

    private String department;
}
