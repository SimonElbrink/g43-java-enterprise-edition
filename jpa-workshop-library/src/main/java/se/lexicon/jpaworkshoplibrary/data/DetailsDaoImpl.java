package se.lexicon.jpaworkshoplibrary.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.jpaworkshoplibrary.entity.Details;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class DetailsDaoImpl implements DetailsDao {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Details create(Details details) {
        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional(readOnly = true)
    public Details findById(int id) {
        return entityManager.find(Details.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Details> findAll() {
        return entityManager.createQuery("SELECT details FROM Details details", Details.class).getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Details update(Details details) {
        return entityManager.merge(details);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(int id) {
        entityManager.remove(findById(id));
    }


}
