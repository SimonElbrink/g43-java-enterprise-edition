package se.lexicon.jpaworkshoplibrary.data;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.jpaworkshoplibrary.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

public class AuthorDaoImpl implements AuthorDao {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Author findById(int id) {
        return entityManager.find(Author.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Author update(Author author) {
        return entityManager.merge(author);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(int id) {
        entityManager.remove(findById(id));
    }
}
