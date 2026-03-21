package com.subham.studentRecord.controller;

import com.google.gson.Gson;
import com.subham.studentRecord.entity.Student;
import com.subham.studentRecord.entity.dto.StudentDto;
import com.subham.studentRecord.exception.StudentException;
import com.subham.studentRecord.model.Response;
import com.subham.studentRecord.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    Gson gson = new Gson();

    @PostMapping()
    public ResponseEntity<String> registerStudent(@RequestBody StudentDto student) throws StudentException {
        log.info("Request hit in controller for Student registration {}", student);
        return ResponseEntity.status(201).body(gson.toJson(studentService.registerNewStudent(student)));
    }

    @GetMapping()
    public ResponseEntity<String> getAllStudents(){
        log.info("Request hit in controller to get all Students");
        return ResponseEntity.ok(gson.toJson(studentService.getAllStudents()));
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getStudentById(@PathVariable Long id) throws StudentException {
        log.info("Request hit in controller to get data of student with Id: {}", id);
        return ResponseEntity.ok(gson.toJson(studentService.getStudentById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateStudentById(@PathVariable Long id, @RequestBody StudentDto student) throws StudentException {
        log.info("Request hit in controller to update student details with id: {}, data: {}", id, student);
        return ResponseEntity.ok(gson.toJson(studentService.updateStudentDetail(id, student)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id) throws StudentException {
        log.info("Request hit in controller to delete data of student with Id: {}", id);
        studentService.deleteStudentById(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("department/{department}")
    public ResponseEntity<String> getStudentsByDepartment(@PathVariable String department){
        log.info("Request hit in controller to get data of students with department: {}", department);
        return ResponseEntity.ok(gson.toJson(studentService.fetchStudentByDepartment(department)));
    }

}
