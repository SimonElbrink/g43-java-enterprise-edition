package se.lexicon.jpaworkshoplibrary.data;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.jpaworkshoplibrary.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

public class BookDaoImpl implements BookDao {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Book findById(int id) {
        return entityManager.find(Book.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(int id) {
        entityManager.remove(findById(id));
    }
}
