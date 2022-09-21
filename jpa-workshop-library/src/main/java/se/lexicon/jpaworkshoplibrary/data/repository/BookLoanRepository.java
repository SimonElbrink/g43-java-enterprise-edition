package se.lexicon.jpaworkshoplibrary.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.jpaworkshoplibrary.entity.BookLoan;

public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {

}
