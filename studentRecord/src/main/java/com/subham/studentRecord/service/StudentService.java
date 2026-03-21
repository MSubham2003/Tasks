package com.subham.studentRecord.service;

import com.subham.studentRecord.entity.Student;
import com.subham.studentRecord.entity.dto.StudentDto;
import com.subham.studentRecord.exception.StudentException;
import com.subham.studentRecord.model.Response;
import org.springframework.stereotype.Service;

public interface StudentService {
    public Response registerNewStudent(StudentDto student) throws StudentException;

    public Response getAllStudents();

    public Response getStudentById(Long id) throws StudentException;

    public Response updateStudentDetail(Long id,StudentDto student) throws StudentException;

    public void deleteStudentById(Long id) throws StudentException;

    public Response fetchStudentByDepartment(String department);
}
