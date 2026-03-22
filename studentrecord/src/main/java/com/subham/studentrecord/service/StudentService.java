package com.subham.studentrecord.service;

import com.subham.studentrecord.entity.dto.StudentDto;
import com.subham.studentrecord.exception.StudentException;
import com.subham.studentrecord.model.Response;

public interface StudentService {
    public Response registerNewStudent(StudentDto student) throws StudentException;

    public Response getAllStudents();

    public Response getStudentById(Long id) throws StudentException;

    public Response updateStudentDetail(Long id,StudentDto student) throws StudentException;

    public void deleteStudentById(Long id) throws StudentException;

    public Response fetchStudentByDepartment(String department);
}
