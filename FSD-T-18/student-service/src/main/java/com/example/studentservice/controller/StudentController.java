package com.example.studentservice.controller;

import com.example.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * GET /students
     * Returns student data from the service layer.
     */
    @GetMapping
    public ResponseEntity<String> getStudents() {
        String data = studentService.getStudent();
        return ResponseEntity.ok(data);
    }
}
