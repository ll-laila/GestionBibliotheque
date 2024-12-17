package com.library;
import com.library.dao.BookDAO;
import com.library.dao.StudentDAO;
import com.library.service.BorrowService;
import com.library.service.BookService;
import com.library.service.StudentService;
import com.library.model.Book;
import com.library.model.Student;
import com.library.model.Borrow;
import com.library.dao.BorrowDAO;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        BookService bookService = new BookService(bookDAO);

        StudentDAO studentDAO = new StudentDAO();
        StudentService studentService = new StudentService(studentDAO);

        BorrowDAO borrowDAO = new BorrowDAO();
        BorrowService borrowService = new BorrowService(borrowDAO);

        Book book1 = new Book(1, "Effective Java", "Joshua Bloch", "123456", "2017",true);
        bookService.addBook(book1);

        Student student1 = new Student(1, "John Doe");
        studentService.addStudent(student1);

        boolean running = true;

        while (running) {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Afficher les livres");
            System.out.println("3. Ajouter un étudiant");
            System.out.println("4. Afficher les étudiants");
            System.out.println("5. Emprunter un livre");
            System.out.println("6. Afficher les emprunts");
            System.out.println("7. Quitter");

            System.out.print("Choisir une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Ajouter un livre
                    System.out.print("Entrez le titre du livre: ");
                    String title = scanner.nextLine();
                    System.out.print("Entrez l'auteur du livre: ");
                    String author = scanner.nextLine();
                    System.out.print("Entrez l'ISBN du livre: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Entrez l'année de publication: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    Book newBook = new Book(2, title, author, isbn, String.valueOf(year),true); // Le 2 est l'ID du livre
                    bookService.addBook(newBook);
                    break;

                case 2:
                    bookService.displayBooks();
                    break;

                case 3:
                    // Ajouter un étudiant
                    System.out.print("Entrez le nom de l'étudiant: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Entrez l'ID de l'étudiant: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();

                    Student newStudent = new Student(studentId, studentName);
                    studentService.addStudent(newStudent);
                    break;
                case 4:
                    studentService.displayStudents();
                    break;

                case 5:
                    // Emprunter un livre
                    System.out.print("Entrez l'ID de l'étudiant: ");
                    int studentIdForBorrow = scanner.nextInt();
                    System.out.print("Entrez l'ID du livre: ");
                    int bookIdForBorrow = scanner.nextInt();
                    scanner.nextLine();

                    // Récupérer l'étudiant et le livre
                    Student studentForBorrow = studentService.findStudentById(studentIdForBorrow).get();
                    var bookForBorrow = bookService.findBookById(bookIdForBorrow);

                    if (bookForBorrow.isPresent()) {
                        Borrow borrow = new Borrow(35,studentForBorrow.getId(), bookForBorrow.get().getId(), new Date(), null);
                        borrowService.borrowBook(borrow);
                    } else {
                        System.out.println("Étudiant ou livre introuvable.");
                    }
                    break;

                case 6:
                    borrowService.displayBorrows();
                    break;

                case 7:
                    running = false;
                    System.out.println("Au revoir!");
                    break;

                default:
                    System.out.println("Option invalide.");
            }
        }

        scanner.close();
    }
}
