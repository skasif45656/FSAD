package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.dto.StudentResponse;
import org.springframework.data.domain.Page;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);

    StudentResponse getStudentById(Long id);

    Page<StudentResponse> getAllStudents(int page, int size, String sortBy, String sortDir);

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deleteStudent(Long id);
}
