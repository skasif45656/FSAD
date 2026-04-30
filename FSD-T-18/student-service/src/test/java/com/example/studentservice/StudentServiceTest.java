package com.example.studentservice;

import com.example.studentservice.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Service Layer Test
 * Uses @SpringBootTest to load the full application context.
 * Validates that StudentService.getStudent() returns the expected value.
 */
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void testGetStudent_ReturnsStudentData() {
        // Act
        String result = studentService.getStudent();

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals("Student Data", result, "Service should return 'Student Data'");
    }

    @Test
    void testGetStudent_IsNotEmpty() {
        String result = studentService.getStudent();
        assertNotNull(result);
        assert !result.isEmpty() : "Result should not be empty";
    }
}
