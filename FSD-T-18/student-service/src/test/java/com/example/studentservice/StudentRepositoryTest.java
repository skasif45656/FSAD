package com.example.studentservice;

import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Repository Layer Test
 * Uses @DataJpaTest to load only the JPA layer with an embedded H2 database.
 * Tests CRUD operations on StudentRepository.
 */
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveStudent_PersistsSuccessfully() {
        // Arrange
        Student student = new Student();
        student.setName("Alice");

        // Act
        Student saved = studentRepository.save(student);

        // Assert
        assertNotNull(saved.getId(), "Saved student should have an auto-generated ID");
        assertEquals("Alice", saved.getName(), "Saved student name should match");
    }

    @Test
    void testFindById_ReturnsCorrectStudent() {
        // Arrange
        Student student = new Student();
        student.setName("Bob");
        Student saved = studentRepository.save(student);

        // Act
        Optional<Student> found = studentRepository.findById(saved.getId());

        // Assert
        assertTrue(found.isPresent(), "Student should be found by ID");
        assertEquals("Bob", found.get().getName(), "Found student name should be 'Bob'");
    }

    @Test
    void testFindAll_ReturnsAllStudents() {
        // Arrange
        Student s1 = new Student(); s1.setName("Charlie");
        Student s2 = new Student(); s2.setName("Diana");
        studentRepository.save(s1);
        studentRepository.save(s2);

        // Act
        List<Student> students = studentRepository.findAll();

        // Assert
        assertEquals(2, students.size(), "Should return exactly 2 students");
    }

    @Test
    void testDeleteById_RemovesStudent() {
        // Arrange
        Student student = new Student();
        student.setName("Eve");
        Student saved = studentRepository.save(student);

        // Act
        studentRepository.deleteById(saved.getId());
        Optional<Student> deleted = studentRepository.findById(saved.getId());

        // Assert
        assertFalse(deleted.isPresent(), "Student should be deleted and not found");
    }
}
