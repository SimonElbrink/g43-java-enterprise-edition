package se.lexicon.jpaworkshoplibrary.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookLoanId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH}
    ,fetch = FetchType.EAGER)
    private AppUser borrower;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH}
            ,fetch = FetchType.EAGER)
    private Book book;


    public BookLoan() {
    }

    public BookLoan(int bookLoanId, LocalDate loanDate, LocalDate dueDate, boolean returned, AppUser borrower, Book book) {
        this.bookLoanId = bookLoanId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;
        this.book = book;
    }

    public BookLoan(LocalDate loanDate, LocalDate dueDate, boolean returned, AppUser borrower, Book book) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;
        this.book = book;
    }

    public int getBookLoanId() {
        return bookLoanId;
    }

    public void setBookLoanId(int bookLoanId) {
        this.bookLoanId = bookLoanId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public AppUser getBorrower() {
        return borrower;
    }

    public void setBorrower(AppUser borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return getBookLoanId() == bookLoan.getBookLoanId() && isReturned() == bookLoan.isReturned() && Objects.equals(getLoanDate(), bookLoan.getLoanDate()) && Objects.equals(getDueDate(), bookLoan.getDueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookLoanId(), getLoanDate(), getDueDate(), isReturned());
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "bookLoanId=" + bookLoanId +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returned=" + returned +
                ", borrower=" + borrower +
                ", book=" + book +
                '}';
    }
}
