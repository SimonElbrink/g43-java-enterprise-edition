package se.lexicon.jpaworkshoplibrary.data;


import se.lexicon.jpaworkshoplibrary.entity.BookLoan;

import java.util.Collection;

public interface BookLoanDao {
    BookLoan create(BookLoan bookLoan);

    BookLoan findById(int id);

    Collection<BookLoan> findAll();

    BookLoan update(BookLoan bookLoan);

    void delete(int id);
}
