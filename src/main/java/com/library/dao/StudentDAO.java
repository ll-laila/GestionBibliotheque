package com.library.dao;

import com.library.model.Student;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    public StudentDAO() {
    }

    public void addStudent(Student student) {
        String sql = "INSERT INTO students (id,name) VALUES (?,?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student inséré avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du Student : " + e.getMessage());
        }
    }


    public Optional<Student>  getStudentById(int id) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student(resultSet.getInt("id"), resultSet.getString("name"));
                    return Optional.of(student);
                } else {
                    LOGGER.log(Level.WARNING, "Aucun étudiant trouvé avec l'ID : " + id);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération de l'étudiant", e);
        }
        return Optional.empty();
    }


    public Optional<Student>  getStudentByName(String name) {
        String query = "SELECT * FROM students WHERE name = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student(resultSet.getInt("id"), resultSet.getString("name"));
                    return Optional.of(student);
                } else {
                    LOGGER.log(Level.WARNING, "Aucun étudiant trouvé avec l'ID : " + name);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération de l'étudiant", e);
        }
        return null;
    }


    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                students.add(new Student(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération des étudiants", e);
        }
        return students;
    }



    public void updateStudent(Student student) {
        String query = "UPDATE students SET name = ? WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("L'étudiant a été mis à jour avec succès !");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la mise à jour de l'étudiant", e);
        }
    }




    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("L'étudiant a été supprimé avec succès.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression de l'étudiant", e);
        }
    }


    public void deleteAllStudents() {
        String query = "DELETE FROM students";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Les étudiants ont été supprimé avec succès.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression des étudiants", e);
        }
    }

}
