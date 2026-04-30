package com.example.studentservice;

import com.example.studentservice.controller.StudentController;
import com.example.studentservice.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller Layer Test
 * Uses @WebMvcTest to load only the web layer (no full context).
 * Uses MockMvc to simulate HTTP requests.
 * Uses @MockBean to mock StudentService dependency.
 */
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void testGetStudents_ReturnsOkWithStudentData() throws Exception {
        // Arrange: mock service layer response
        when(studentService.getStudent()).thenReturn("Student Data");

        // Act + Assert: perform GET /students and verify status + body
        mockMvc.perform(get("/students"))
               .andExpect(status().isOk())
               .andExpect(content().string("Student Data"));
    }

    @Test
    void testGetStudents_EndpointIsReachable() throws Exception {
        when(studentService.getStudent()).thenReturn("Student Data");

        mockMvc.perform(get("/students"))
               .andExpect(status().isOk());
    }
}
