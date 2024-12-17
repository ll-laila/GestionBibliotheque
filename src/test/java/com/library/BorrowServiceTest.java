package com.library;

import com.library.dao.BookDAO;
import com.library.dao.BorrowDAO;
import com.library.dao.StudentDAO;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.service.BookService;
import com.library.service.BorrowService;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BorrowServiceTest {

    private BookService bookService;
    private StudentService studentService;
    private BorrowService borrowService;



    @BeforeEach
    void setUp() {

        StudentDAO studentDAO = new StudentDAO();
        studentService = new StudentService(studentDAO);

        BookDAO bookDAO = new BookDAO();
        bookService = new BookService(bookDAO);

        BorrowDAO borrowDAO = new BorrowDAO();
        borrowService = new BorrowService(borrowDAO);

    }

    @Test
    void testBorrowBook() {
//        studentService.addStudent(new Student(1, "laila"));
//        studentService.addStudent(new Student(2, "Amale"));
//        bookService.addBook(new Book(1, "Java Programming", "John Doe", "123456", "2020",true));
//        bookService.addBook(new Book(2, "Advanced Java", "Bob", "7891011", "2021",true));
//
//        Borrow borrow = new Borrow(1,1,1,new Date(),new Date());
//        assertEquals("Livre emprunté avec succès!", borrowService.borrowBook(borrow));
//        Optional<Book> bookOpt = bookService.findBookById(1);
//        if (bookOpt.isPresent()) {
//            assertFalse(bookOpt.get().isAvailable());
//        } else {
//            fail("Student not found!");
//        }
//        bookService.deleteAllBook();
//        studentService.deleteAllStudents();
//        borrowService.deleteAllBorrows();
    }

    @Test
    void testReturnBook() {
//        bookService.deleteAllBook();
//        studentService.deleteAllStudents();
//        borrowService.deleteAllBorrows();
//
//        studentService.addStudent(new Student(2, "Amale"));
//        bookService.addBook(new Book(2, "Advanced Java", "Bob", "7891011", "2021",true));
//        Borrow borrow = new Borrow(2,2,2,new Date(),new Date());
//        borrowService.borrowBook(borrow);
//        assertEquals("Livre retourné avec succès!", borrowService.returnBook(2, 2));
//        Optional<Book> bookOpt = bookService.findBookById(1);
//        if (bookOpt.isPresent()) {
//            assertTrue(bookOpt.get().isAvailable());
//        } else {
//            fail("livre not found!");
//        }
//        bookService.deleteAllBook();
//        studentService.deleteAllStudents();
//        borrowService.deleteAllBorrows();
    }

    @Test
    void testBorrowBookNotAvailable() {
//        Borrow borrow = new Borrow(-1,1,1,new Date(),null);
//        Book book = bookDAO.getBookById(1).get();
//        book.setAvailable(false);
//        borrowService.borrowBook(borrow);
//        assertFalse(book.isAvailable());
    }

    @Test
    void testBorrowBookStudentNotFound() {
//        Borrow borrow = new Borrow(-1,90,1,new Date(),null);
//        assertEquals("Étudiant ou livre non trouvé.", borrowService.borrowBook(borrow));
    }
}
