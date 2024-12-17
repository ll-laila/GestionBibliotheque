package com.library.service;
import com.library.dao.StudentDAO;
import com.library.model.Student;
import java.util.List;
import java.util.Optional;


public class StudentService {
    private final StudentDAO studentDAO;


    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }


    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }


    public void displayStudents() {
        List<Student> students = studentDAO.getAllStudents();
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + " | Nom: " + student.getName());
        }
    }

    public List<Student> allStudents() {
        return studentDAO.getAllStudents();
    }


    public Optional<Student> findStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    public Optional<Student> getStudentByName(String name) {
        return studentDAO.getStudentByName(name);
    }


    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }


    public void deleteStudent(int id) {
        studentDAO.deleteStudent(id);
    }

    public void deleteAllStudents() {
        studentDAO.deleteAllStudents();
    }


}
