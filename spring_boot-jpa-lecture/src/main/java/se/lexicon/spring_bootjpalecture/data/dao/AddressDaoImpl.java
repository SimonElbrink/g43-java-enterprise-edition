package se.lexicon.spring_bootjpalecture.data.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.spring_bootjpalecture.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
    public List<Address> findAddressByZipCode(String zipCode) {
        List<Address> foundAddresses = new ArrayList<>();

        TypedQuery<Address> query = entityManager.createQuery("SELECT a FROM addresses a WHERE a.zipCode = :zc", Address.class);
        query.setParameter("zc", zipCode);

        foundAddresses = query.getResultList();

        return foundAddresses;
    }


    @Override
    public List<Address> findAddressByCity(String city) {
        List<Address> foundAddresses = new ArrayList<>();

        TypedQuery<Address> query = entityManager.createQuery("SELECT a FROM addresses a WHERE a.city = :city", Address.class);
        query.setParameter("city", city);

        foundAddresses = query.getResultList();

        return foundAddresses;
    }

    @Override
    @Transactional(rollbackFor = IllegalArgumentException.class)
    public void remove(Address address) {
        findById(address.getId()).orElseThrow(() -> new IllegalArgumentException("Data nor found Exception"));
        entityManager.remove(address);
    }
}