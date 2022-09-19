package se.lexicon.jpaworkshoplibrary.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.jpaworkshoplibrary.entity.BookLoan;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class BookLoanDaoImpl implements BookLoanDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public BookLoan findById(int id) {
        return entityManager.find(BookLoan.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<BookLoan> findAll() {
        return entityManager.createQuery("SELECT bl FROM BookLoan bl", BookLoan.class).getResultList();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public BookLoan create(BookLoan bookLoan) {
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public BookLoan update(BookLoan bookLoan) {
        return entityManager.merge(bookLoan);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(int id) {
        entityManager.remove(findById(id));
    }
}
