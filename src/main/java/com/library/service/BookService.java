package com.library.service;
import com.library.dao.BookDAO;
import com.library.model.Book;
import java.util.List;
import java.util.Optional;


public class BookService {

    private final BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    public void addBook(Book book) {
        bookDAO.add(book);
    }


    public void displayBooks() {
        List<Book> books = bookDAO.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public  List<Book> allBooks() {
      return bookDAO.getAllBooks();
    }

    public Optional<Book> findBookById(int id) {
        return bookDAO.getBookById(id);
    }

    public void deleteBook(int id) {
        bookDAO.delete(id);
    }

    public void deleteAllBook() {
        bookDAO.deleteAllBook();
    }

    public void updateBook(Book book) {
        bookDAO.update(book);
    }


}
