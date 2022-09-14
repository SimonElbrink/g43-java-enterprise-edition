package se.lexicon.jpaworkshoplibrary.data;

import org.springframework.stereotype.Repository;
import se.lexicon.jpaworkshoplibrary.entity.BookLoan;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class BookLoanDaoImpl implements BookLoanDao{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BookLoan create(BookLoan bookLoan) {

        entityManager.persist(bookLoan);

        return bookLoan;
    }

    @Override
    public BookLoan findById(int id) {
        return null;
    }

    @Override
    public Collection<BookLoan> findAll() {
        return entityManager.createQuery("SELECT bl FROM BookLoan bl", BookLoan.class).getResultList();
    }

    @Override
    public BookLoan update(BookLoan bookLoan) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
