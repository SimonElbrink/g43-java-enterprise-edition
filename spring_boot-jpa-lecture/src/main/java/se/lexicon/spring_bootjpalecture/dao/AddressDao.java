package se.lexicon.spring_bootjpalecture.dao;

import se.lexicon.spring_bootjpalecture.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDao {

    Address save(Address address);

    Optional<Address> findById(int id);

    List<Address> findAll();

    void remove(Address address);
}
