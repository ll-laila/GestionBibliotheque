package com.library;
import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;



class StudentServiceTest {
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        StudentDAO studentDAO = new StudentDAO();
        studentService = new StudentService(studentDAO);
    }

    @Test
    void testAddStudent() {
        studentService.deleteAllStudents();
        Student student = new Student(1,"Alice1");
        studentService.addStudent(student);
        assertEquals(1, studentService.allStudents().size());
        Optional<Student> studentOpt = studentService.findStudentById(1);
        if (studentOpt.isPresent()) {
            assertEquals(1, studentOpt.get().getId());
        } else {
            fail("Student not found!");
        }
        studentService.deleteStudent(1);
    }



    @Test
    void testUpdateStudent() {
        studentService.deleteAllStudents();
        Student student = new Student(2,"Alice2");
        Student studentUpdate = new Student(2,"Alice Smith");
        studentService.addStudent(student);
        studentService.updateStudent(studentUpdate);
        Optional<Student> studentUp = studentService.findStudentById(2);
        if (studentUp.isPresent()) {
            assertEquals("Alice Smith", studentUp.get().getName());
        } else {
            fail("Student not found!");
        }
        studentService.deleteStudent(2);
    }



    @Test
    void testDeleteStudent() {
        Student student = new Student(3,"Alice3");
        studentService.addStudent(student);
        studentService.deleteStudent(3);
        assertTrue(studentService.findStudentById(3).isEmpty());
    }

    @Test
    void testGetAllStudents() {
        studentService.deleteAllStudents();
        Student student1 = new Student(4,"Alice4");
        Student student2 = new Student(5,"Bob");
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        assertEquals(2, studentService.allStudents().size());
        studentService.deleteAllStudents();
    }
}
