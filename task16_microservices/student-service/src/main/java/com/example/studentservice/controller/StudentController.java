package com.example.studentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/students")
    public String getStudents() {
        return "Response from Student Service - Port: " + serverPort;
    }
}
