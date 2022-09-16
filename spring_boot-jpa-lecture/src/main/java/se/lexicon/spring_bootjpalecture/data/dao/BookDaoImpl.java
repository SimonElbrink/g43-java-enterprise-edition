package se.lexicon.spring_bootjpalecture.data.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.spring_bootjpalecture.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(rollbackFor = IllegalArgumentException.class)
    @Override
    public Book save(Book book) {

        if (book == null) throw new IllegalArgumentException("Book not allowed to be null");

        if (book.getId() <= 0) {
            entityManager.persist(book); // All New object
            return book;
        } else {
            entityManager.merge(book); // Attached an already saved object.
        }
        return book;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(int id) {
        Book book = entityManager.find(Book.class, id);
        return Optional.ofNullable(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void remove(int bookId) {
        findById(bookId).ifPresent(entityManager::remove);
    }
}
