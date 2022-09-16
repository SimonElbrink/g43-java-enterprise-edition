package se.lexicon.spring_bootjpalecture.data.dao;

import se.lexicon.spring_bootjpalecture.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDao {

    Address save(Address address);

    Optional<Address> findById(int id);

    List<Address> findAll();


    List<Address> findAddressByZipCode(String zipCode);

    List<Address> findAddressByCity(String city);



    void remove(Address address);
}
