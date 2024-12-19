package com.library;
import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.model.Student;
import com.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class BookServiceTest {
    private BookService bookService;

    @BeforeEach
    void setUp() {
        BookDAO bookDAO = new BookDAO();
        bookService = new BookService(bookDAO);
    }

    @Test
    void testAddBook() {
        bookService.deleteAllBook();
        Book book = new Book(1, "Java Programming", "John Doe", "123456", "2020",true);
        bookService.addBook(book);
        assertEquals(1, bookService.allBooks().size());
        assertTrue(bookService.findBookById(1).isPresent());
        assertEquals("Java Programming", bookService.findBookById(1).get().getTitle());
        bookService.deleteAllBook();
    }

    @Test
    void testUpdateBook() {
        bookService.deleteAllBook();
        Book book = new Book(2, "Java Programming", "John Doe", "123456", "2020",true);
        bookService.addBook(book);

        Book bookUpdate = new Book(2, "Advanced Java", "John Doe", "654321", "2021",true);
        bookService.updateBook(bookUpdate);

        assertTrue(bookService.findBookById(2).isPresent());
        Optional<Book> bookOpt = bookService.findBookById(2);

        if (bookOpt.isPresent()) {
            assertEquals("Advanced Java",bookOpt.get().getTitle());
            assertEquals("654321", bookOpt.get().getIsbn());
            assertEquals("2021", bookOpt.get().getPublishedYear());
        } else {
            fail("Student not found!");
        }

        bookService.deleteAllBook();
    }



    @Test
    void testDeleteBook() {
        bookService.deleteAllBook();
        Book book = new Book(3, "Java Programming", "John Doe", "123456", "2020",true);
        bookService.addBook(book);
        bookService.deleteBook(3);
        assertFalse(bookService.findBookById(3).isPresent());
    }
}
