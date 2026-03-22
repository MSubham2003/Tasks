package com.subham.studentrecord.controller;

import com.subham.studentrecord.entity.dto.StudentDto;
import com.subham.studentrecord.model.Response;
import com.subham.studentrecord.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<Response> registerStudent(@RequestBody StudentDto student) {
        log.info("Request hit in controller for Student registration {}", student);
        return ResponseEntity.status(201).body(studentService.registerNewStudent(student));
    }

    @GetMapping()
    public ResponseEntity<Response> getAllStudents() {
        log.info("Request hit in controller to get all Students");
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getStudentById(@PathVariable Long id) {
        log.info("Request hit in controller to get data of student with Id: {}", id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Response> updateStudentById(@PathVariable Long id, @RequestBody StudentDto student) {
        log.info("Request hit in controller to update student details with id: {}, data: {}", id, student);
        return ResponseEntity.ok(studentService.updateStudentDetail(id, student));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        log.info("Request hit in controller to delete data of student with Id: {}", id);
        studentService.deleteStudentById(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("department/{department}")
    public ResponseEntity<Response> getStudentsByDepartment(@PathVariable String department) {
        log.info("Request hit in controller to get data of students with department: {}", department);
        return ResponseEntity.ok(studentService.fetchStudentByDepartment(department));
    }

}
