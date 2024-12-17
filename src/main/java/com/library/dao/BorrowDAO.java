package com.library.dao;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BorrowDAO {

    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    private final BookDAO bookDAO;
    private final StudentDAO studentDAO;

    public BorrowDAO() {
        this.bookDAO = new BookDAO();
        this.studentDAO = new StudentDAO();
    }


    public List<Borrow> getAllBorrows() {
        List<Borrow> borrows = new ArrayList<>();
        String query = "SELECT * FROM borrows";
        try (Connection connection = DbConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Borrow borrow = new Borrow(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getInt("book_id"),
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date")
                );
                borrows.add(borrow);
            }
            for (Borrow borrow : borrows) {
                System.out.println("ID: " + borrow.getId()+ " |Student : " + borrow.getStudentId()+" |book : " + borrow.getBookId()+  " |borrow_date : " + borrow.getBorrowDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }



    public String save(Borrow borrow) {
        String query = "INSERT INTO borrows (id,student_id, book_id, borrow_date, return_date) VALUES (?,?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, borrow.getId());
            stmt.setInt(2, borrow.getStudentId());
            stmt.setInt(3, borrow.getBookId());
            stmt.setDate(4, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.setDate(5, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.executeUpdate();
            return "Livre emprunté avec succès!";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public String returnBook(int studentId, int bookId) {
        var student = studentDAO.getStudentById(studentId);
        var book = bookDAO.getBookById(bookId);

        if (student.isEmpty() || book.isEmpty()) {
            return "Étudiant ou livre non trouvé.";
        }
        book.get().setAvailable(true);
        bookDAO.update(book.get());
        return "Livre retourné avec succès!";
    }


    public String borrowBook(Borrow borrow) {
        var student = studentDAO.getStudentById(borrow.getStudentId());
        Optional<Book> book = bookDAO.getBookById(borrow.getBookId());

        if (student.isEmpty() || book.isEmpty()) {
            return "Étudiant ou livre non trouvé.";
        }

        if (!book.get().isAvailable()) {
            return "Le livre n'est pas disponible.";
        }

        var borrowSave = save(borrow);
        System.out.println("borrow inséré avec succès !");
        book.get().setAvailable(false);
        bookDAO.update(book.get());

        return "Livre emprunté avec succès!";
    }

    public void deleteAllBorrows() {
        String query = "DELETE FROM borrows";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Les borrows ont été supprimé avec succès.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression des borrows", e);
        }
    }



}
