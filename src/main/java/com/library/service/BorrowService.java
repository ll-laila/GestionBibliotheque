
package com.library.service;
import com.library.dao.BorrowDAO;
import com.library.model.Borrow;

import java.util.List;

public class BorrowService {

    private final BorrowDAO borrowDAO;

    public BorrowService(BorrowDAO borrowDAO) {
        this.borrowDAO = borrowDAO;
    }


    public void displayBorrows() {
        System.out.println("Liste des emprunts...");
        borrowDAO.getAllBorrows();
    }

    public List<Borrow> allBorrows() {
        return borrowDAO.getAllBorrows();
    }

    public void deleteAllBorrows() {
         borrowDAO.deleteAllBorrows();
    }

    public String returnBook(int studentId, int bookId) {
        return borrowDAO.returnBook(studentId,bookId);
    }


    public short borrowBook(Borrow borrow) {
        borrowDAO.borrowBook(borrow);
        return 0;
    }

}
