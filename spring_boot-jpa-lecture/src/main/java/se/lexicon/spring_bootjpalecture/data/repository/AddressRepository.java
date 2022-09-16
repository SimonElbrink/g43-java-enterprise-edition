package se.lexicon.spring_bootjpalecture.data.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.spring_bootjpalecture.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
