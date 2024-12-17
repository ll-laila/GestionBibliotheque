package com.library.dao;

import com.library.model.Book;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BookDAO {

    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    public void add(Book book) {
        String sql = "INSERT INTO books (id,title, author, isbn, published_year,available) VALUES (?,?,?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getIsbn());
            statement.setString(5, book.getPublishedYear());
            statement.setBoolean(6, book.isAvailable());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Livre inséré avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du livre : " + e.getMessage());
        }
    }




    public Book getBookByIsbn(String isbn) {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        Book book = null;
        
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setPublishedYear(resultSet.getString("published_year"));
                book.setAvailable(resultSet.getBoolean("available"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du livre : " + e.getMessage());
        }
        
        return book;
    }
    



    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        
        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
             
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setPublishedYear(resultSet.getString("published_year"));
                book.setAvailable(resultSet.getBoolean("available"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des livres : " + e.getMessage());
        }
        
        return books;
    }




    public Optional<Book> getBookById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Book book = new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("isbn"),
                            resultSet.getString("published_year"),
                            resultSet.getBoolean("available")
                    );
                    return Optional.of(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }





    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Le livre a été supprimé avec succès.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du livre : " + e.getMessage());
        }
    }




    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, published_year = ?, available = ? WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setString(4, book.getPublishedYear());
            statement.setInt(5, book.getId());
            statement.setBoolean(6, book.isAvailable());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Le livre a été mis à jour avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du livre : " + e.getMessage());
        }
    }

    public void deleteAllBook() {
        String query = "DELETE FROM books";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Les books ont été supprimé avec succès.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression des books", e);
        }
    }

}
