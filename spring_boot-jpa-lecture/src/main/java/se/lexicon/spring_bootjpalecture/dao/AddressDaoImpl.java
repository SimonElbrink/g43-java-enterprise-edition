package se.lexicon.spring_bootjpalecture.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.spring_bootjpalecture.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(rollbackFor = IllegalArgumentException.class)
    public Address save(Address address) {
        if (address == null) throw new IllegalArgumentException("Address not allowed to be null");

        if (address.getId() <= 0) {
            entityManager.persist(address); // All New object
            return address;
        } else {
            entityManager.merge(address); // Attached an already saved object.
        }
        return address;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Address> findById(int id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Address> findAll() {
        return entityManager.createQuery("Select a from addresses a", Address.class).getResultList();
    }

    @Override
    @Transactional(rollbackFor = IllegalArgumentException.class)
    public void remove(Address address) {
        findById(address.getId()).orElseThrow(() -> new IllegalArgumentException("Data nor found Exception"));
        entityManager.remove(address);
    }
}