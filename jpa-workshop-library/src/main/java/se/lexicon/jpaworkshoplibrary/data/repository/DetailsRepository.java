package se.lexicon.jpaworkshoplibrary.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.jpaworkshoplibrary.entity.Details;

public interface DetailsRepository extends JpaRepository<Details, Integer> {

}
