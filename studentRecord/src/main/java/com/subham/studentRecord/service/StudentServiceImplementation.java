package com.subham.studentRecord.service;

import com.subham.studentRecord.entity.Student;
import com.subham.studentRecord.entity.dto.StudentDto;
import com.subham.studentRecord.exception.StudentException;
import com.subham.studentRecord.model.Response;
import com.subham.studentRecord.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentServiceImplementation implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PojoValidator validator;

    @Autowired
    private StudentMapper studentMapper;

    private static final String SUCCESS_STATUS = "SUCCESS";

    @Override
    public Response registerNewStudent(StudentDto studentDto) throws StudentException {
        Student student = studentMapper.toEntity(studentDto);
        validator.validate(student);
        Optional<Student> dbRes = studentRepository.findIfEmailExist(student.getEmail());
        log.info("Data received from db for unique email check {}", dbRes);
        if (dbRes.isPresent()) {
            log.info("Email is already present in db. Student registered with email {}", dbRes);
            throw new StudentException(HttpStatusCode.valueOf(409), "Email should be unique");
        }
        studentRepository.save(student);
        log.info("Student registered with name: {}", student.getName());
        Response res = new Response();
        res.setStatus(SUCCESS_STATUS);
        res.setData(studentDto);
        return res;
    }

    @Override
    public Response getAllStudents() {
        Response res = new Response();
        res.setStatus(SUCCESS_STATUS);
        List<Student> students = studentRepository.findAll();
        res.setData(students);
        return res;
    }

    @Override
    public Response getStudentById(Long id) throws StudentException {
        Response res = new Response();
        res.setStatus(SUCCESS_STATUS);
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty())
            throw new StudentException(HttpStatusCode.valueOf(404), "Student not found with id " + id);
        log.info("Student found with id: {}, data {}", id, student);
        StudentDto responseDto = studentMapper.toDTO(student.get());
        res.setData(responseDto);
        return res;
    }

    @Override
    public Response updateStudentDetail(Long id, StudentDto studentDto) throws StudentException {
        Student student = studentMapper.toEntity(studentDto);
        Response res = new Response();
        res.setStatus(SUCCESS_STATUS);
        Optional<Student> studentData = studentRepository.findById(id);
        if (studentData.isEmpty())
            throw new StudentException(HttpStatusCode.valueOf(404), "Student not found with id " + id);
        log.info("Student found with id: {}, data {}", id, studentData);

        studentData.get().setName(student.getName());
        studentData.get().setEmail(student.getEmail());
        studentData.get().setPhone(student.getPhone());
        studentData.get().setDepartment(student.getDepartment());

        studentRepository.save(studentData.get());
        log.info("Student updated with id: {}, data {}", id, studentData);

        StudentDto responseDto = studentMapper.toDTO(studentData.get());
        res.setData(responseDto);
        return res;
    }

    @Override
    public void deleteStudentById(Long id) throws StudentException {
        Optional<Student> studentData = studentRepository.findById(id);
        if (studentData.isEmpty())
            throw new StudentException(HttpStatusCode.valueOf(404), "Student not found with id " + id);
        log.info("Student found with id: {}, data {}", id, studentData);
        studentRepository.deleteById(id);
    }

    @Override
    public Response fetchStudentByDepartment(String department) {
        Response res = new Response();
        res.setStatus(SUCCESS_STATUS);
        List<Student> studentData = studentRepository.findStudentsByDepartment(department);
        List<StudentDto> studentDtoList = studentData.stream().map(studentMapper::toDTO).toList();
        res.setData(studentDtoList);
        return res;
    }
}
